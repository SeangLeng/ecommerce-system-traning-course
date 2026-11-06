How to run sentinel:

### Initialize
```shell
java -Dserver.port=8081 -Dcsp.sentinel.dashboard.server=localhost:8081 -Dproject.name=sentinel-dashboard -Dsentinel.dashboard.auth.username=sentinel -Dsentinel.dashboard.auth.password=123456 -Dserver.servlet.session.timeout=7200 -jar sentinel-dashboard-1.8.10.jar
```

### Next time
```shell
java -Dserver.port=8081 -Dcsp.sentinel.dashboard.server=localhost:8081 -Dproject.name=sentinel-dashboard sentinel-dashboard-1.8.10.jar
```

Default account:
Username: "sentinel"
Password: "123456"