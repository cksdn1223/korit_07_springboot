
# 📜 Spring Boot 기반 JWT 인증 시스템 동작 원리

제공된 코드를 바탕으로 JWT(JSON Web Token) 인증/인가 흐름을 단계별로 분석합니다. 이 시스템은 크게 **로그인 및 토큰 발급 (인증)**과 **토큰을 사용한 API 접근 (인가)**의 두 가지 흐름으로 구성됩니다.

---

## 1단계: 로그인 및 JWT 발급 (Authentication)

사용자가 아이디와 비밀번호를 제출하면, 서버는 이를 확인하고 **'출입증'** 역할을 하는 JWT를 생성하여 응답합니다.

### 📌 흐름 요약

> `POST /login` 요청 ➡️ **LoginController** (요청 수신) ➡️ **AuthenticationManager** (사용자 인증) ➡️ **UserDetailsServiceImpl** (DB 조회/비밀번호 비교) ➡️ **JwtService** (토큰 생성) ➡️ 클라이언트에 토큰 응답

### 🛠️ 파일별 역할 분석

| 파일 | 역할 | 핵심 코드 동작 |
| :--- | :--- | :--- |
| **`LoginController.java`** | **인증 시작 및 토큰 응답** | 1. 요청 데이터를 `UsernamePasswordAuthenticationToken`으로 포장. 2. `authenticationManager.authenticate()`를 호출하여 **인증 요청**. 3. 인증 성공 시, `jwtService.getToken()`으로 **JWT 생성**. 4. 생성된 JWT를 `Authorization: Bearer <토큰>` 헤더에 담아 클라이언트에게 반환. |
| **`UserDetailsServiceImpl.java`** | **실제 사용자 정보 확인** | 1. `username`을 기반으로 **DB(`userRepository`)에서 사용자 정보**(`AppUser`)를 조회. 2. 사용자가 존재하면, Spring Security가 사용할 수 있도록 **`UserDetails` 객체를 빌드** (암호화된 비밀번호와 Role 포함). 3. 이 정보를 바탕으로 Spring Security가 입력된 평문 비밀번호와 DB의 암호화된 비밀번호를 비교하여 인증을 완료. |
| **`JwtService.java`** (토큰 생성 부분) | **JWT 생성** | `Jwts.builder()`를 사용해 JWT를 생성. 토큰의 `subject` (주인)에 **인증된 사용자 이름**을 넣고, 만료 시간을 설정한 후, 서버만 아는 **비밀키(`key`)로 서명**하여 토큰 위조를 방지. |

---

## 2단계: 발급받은 JWT로 API 접근 (Authorization)

클라이언트는 발급받은 JWT를 모든 요청 헤더에 포함하여 보냅니다. 서버는 요청마다 이 토큰이 유효한지 검사하여 보호된 리소스에 대한 접근 권한을 부여합니다.

### 📌 흐름 요약

> 보호된 API 요청 ➡️ **AuthenticationFilter** (모든 요청 가로채기) ➡️ **JwtService** (토큰 해석 및 검증) ➡️ **SecurityContextHolder** (사용자 인증 정보 등록) ➡️ Controller 실행 (인가 성공)

### 🛠️ 파일별 역할 분석

| 파일 | 역할 | 핵심 코드 동작 |
| :--- | :--- | :--- |
| **`AuthenticationFilter.java`** | **JWT 검사 문지기** | 1. 요청 헤더에서 `Authorization` 값을 확인하고 JWT(`jws`) 추출. 2. `jwtService.getAuthUser()`를 호출하여 **토큰을 해석하고 사용자 이름을 얻음**. 3. 사용자 이름이 유효하면, `SecurityContextHolder`에 `Authentication` 객체를 등록하여 해당 요청이 **인증된 사용자**로부터 왔음을 Spring Security에 알림. |
| **`JwtService.java`** (토큰 해석 부분) | **JWT 해석 및 검증** | 1. `HttpHeaders.AUTHORIZATION` 헤더에서 **토큰 문자열**을 추출. 2. `Jwts.parser().verifyWith(key)`를 사용해 토큰의 **서명**을 검증. (서명이 잘못되면 예외 발생) 3. 서명 검증 후 토큰의 **Payload**에서 **`subject` (사용자 이름)**을 추출하여 반환. |
| **`SecurityConfig.java`** | **시스템 설정 및 필터 배치** | 1. **접근 제어 규칙** 정의: `/login` 등은 `permitAll()`로 허용하고, 나머지 요청은 `authenticated()`로 **로그인(인증) 필수**로 설정. 2. **`AuthenticationFilter`를 Spring Security의 필터 체인에 명시적으로 등록**(`addFilterBefore`), 모든 요청이 실제 컨트롤러에 도달하기 전에 JWT 검사를 거치도록 만듦. |

---

이 두 흐름을 통해 사용자는 한 번의 로그인(인증)으로 JWT를 발급받고, 이 토큰을 만료될 때까지 계속 사용하여 서버의 보호된 리소스에 접근(인가)할 수 있게 됩니다.