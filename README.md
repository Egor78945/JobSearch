<div align="center">
  <h1>Job Search</h1>
  <h2>Приложение для облегчения поиска работы</h2>
</div>

<br>
<div>
  <h2>Описание</h2>
  Job Search - это умный поисковик вакансий, который использует семантический анализ найденных предложений о работе для отсеивания тех, которые не совпадают с запросом пользователя.
  
  В отличие от обычных систем, ищущих по ключевым словам, он анализирует твое развернутое описание желаемой работы и находит самые релевантные предложения на просторах интернета.
</div>

<div>
  <h2>Функционал</h2>

  <h3>Регистрация</h3>  
  Для использования основного функционала приложения пользователю требуется зарегистрироваться в системе.

  <b>POST /auth/register</b>  
  
  <b>Request headers:</b>  
  Content-Type: application/json

  <b>Request body:</b>  
  {  
  "email": "xxx",  
  "password": "xxx"  
  }  
  <h3>Получение пары access и refresh токенов</h3>  
  Основной функционал доступен только авторизованным пользователям.

  <b>POST /auth/login</b>  
  
  <b>Request headers:</b>  
  Content-Type: application/json

  <b>Request body:</b>  
  {  
  "email": "xxx",  
  "password": "xxx"  
  }  

  <b>Response body:</b>  
  {  
  "accessToken": "xxx",  
  "expiresIn": "xxx"  
  }  

  <b>Response headers:</b>  
  X-Refresh-Token: xxx  

  <h3>Обновление пары access и refresh токенов.</h3>  
  По истечении срока службы access-токена требуется получить новую пару токенов, используя refresh-токен.

  <b>POST /auth/refresh</b>  
  
  <b>Request headers:</b>  
  Content-Type: application/json  
  X-Refresh-Token: xxx  

  <b>Response body:</b>  
  {  
  "accessToken": "xxx",  
  "expiresIn": "xxx"  
  }  

  <b>Response headers:</b>  
  X-Refresh-Token: "xxx"
  
</div>
