## 数据库

#### 用户表（users）

| id     | username    | password     | role |
|--------|-------------|--------------|------|
| 自增主键   | 用户名         | 哈希后的密码       | 用户角色 |
| SERIAL | VARCHAR(50) | VARCHAR(200) | INT  |

#### 交通事故表（accidents）

| id     | coordinate            | description | time      | is_resolved |
|--------|-----------------------|-------------|-----------|-------------|
| 自增主键   | 事故地点坐标                | 事故描述        | 发生时间      | 是否已解决       |
| SERIAL | geometry(POINT, 4326) | TEXT        | TIMESTAMP | BOOLEAN     |

#### 警力表（polices）

| id     | name        | phone       |     |
|--------|-------------|-------------|-----|
| 自增主键   | 姓名          | 联系电话        |     |
| SERIAL | VARCHAR(10) | VARCHAR(50) |     |

## API