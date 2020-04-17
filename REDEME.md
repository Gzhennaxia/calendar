src/main/java/com/example/calendar/controller/google/calendar/authorize 下的controller都是需要访问谷歌的接口
/Users/libo/Documents/GitHub/calendar/src/main/java/com/example/calendar/controller 下的controller则是访问自身数据库的数据

对于'提醒'事件来说，他的 event_id 是相同的，也就是说在save时的重复过滤要考虑多个event的id相同的情况。


## [数据库](./DATASOURCE.md)