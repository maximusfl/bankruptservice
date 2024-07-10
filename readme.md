
### bankruptservice
Приложение, написанное на языке java, предоставляющее информацию о клиентах банка, имеющий статус "Банкрот" в виде
веб-страницы и выгрузки в формате excel.

### Технологии в проекте:

### Greenplum</br>
<u>/scripts/greenplum/greenplum.py</u>  python-скрипт для установки и запуска кластера greenplum. Для подключения со стороны приложения - специальные конфиги не требуются, используется драйвер PostgreSQL

### Python </br>
<u>/scripts/excel_csv.py</u> - скрипт конвертации excel to csv для последующей обработки хадупом.

### Java </br>
<u>POST api/v1/report</u> - генерирует список клиентов банка, находящихся в состоянии "Банкорот" формате excel
так же данные из отчёта сохраняются в базу данных для последубщего отображения на веб-странице.

<u>POST api/v1/bankruptcy</u> - возвращает html-страницу со списком клиентов-банкротов

### Hadoop</br>
<u>GET api/v1/bankruptcy</u> - запускает mapReduce задачу, описанную в классе ru.teamsource.bankruptservice.service.HadoopPersonProcessing;
результат выполнения задачи - файл с простой аналитикой (creditScore, totalCreditScore, application_history, credit_requests_history)

### Javascript </br>
<u>/main/resources/static/js</u> - скрипт анимации html-страницы

### PL/SQL</br>
<u>src/main/resources/sql/fetch_person_data.sql</u> - хранимая процедура для выборки данный по Person, вызывается из кода в ru.teamsource.bankruptservice.dao.PersonDao;

