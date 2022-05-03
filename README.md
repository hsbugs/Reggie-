#### 前端配置类路径

```properties
com.itheima.reggie
config
WebMvcConfig:默认情况下 访问不到backend 和 front下的加界面  要创建配置类
```

#### 实体类路径

```properties
com.itheima.reggie
entity
Employee:后台登录员工实体类
```

#### 服务返回结果路径

```properties
com.itheima.reggie
common
R:通用返回结果类 服务端相应的数据 最终都会封装成此对象
```



### 功能实现

#### 后台登录实现

![](https://hs-1257929486.cos.ap-chengdu.myqcloud.com/uPic/o9ZIGS.png)

```properties
com.itheima.reggie.controller
EmployeeController.login:登录功能实现
```

#### 后台退出实现

![](https://hs-1257929486.cos.ap-chengdu.myqcloud.com/uPic/m6niSQ.png)

```properties
com.itheima.reggie.controller
EmployeeController.loguut:退出功能实现
```

#### 后台过滤器的实现

![](https://hs-1257929486.cos.ap-chengdu.myqcloud.com/uPic/9Ut8V5.png)

```properties
com.itheima.reggie.filter
LoginCheckFilter:后台登录过滤
```

#### 新增员工的实现

```properties
com.itheima.reggie.controller
EmployeeController
sava:新增员工
```

##### 新增员工完善

![](https://hs-1257929486.cos.ap-chengdu.myqcloud.com/uPic/Ucc44h.png)

```properties
com.itheima.reggie
common
GlobalExceptionHandler:捕获处理添加异常
```

#### 员工信息分页查询

```properties
com.itheima.reggie.controller
EmployeeController.page:退出功能实现
```

#### 启用禁用员工账号

```properties
com.itheima.reggie.controller
EmployeeController.update:退出功能实现	//根据id修改员工信息
要在com.itheima.reggie.config.WebMvcConfig启动类中 拓展mvc消息转换器:extendMessageConverters
```

#### 编辑员工信息

```properties
com.itheima.reggie.controller
EmployeeController.getById:
```



### 新增分类

```properties
com.itheima.reggie.controller
CategoryController
save:新增分类
```

#### 分类信息分页查询

```properties
com.itheima.reggie.controller
CategoryController
page:新增分页
```

#### 删除分类管理

```properties
com.itheima.reggie.controller
CategoryController
delete:删除分页
```

#### 修改分类管理

```properties
com.itheima.reggie.controller
CategoryController
update:删除分页
```

#### 文件上传

```properties
com.itheima.reggie.controller
CommonController
upload:文件上传
```

#### 文件下载

```properties
com.itheima.reggie.controller
CommonController
download:文件下载
```

#### 菜品管理

````properties
com.itheima.reggie.controller
DishController
save:新增菜品
````

#### 手机验证码

```properties
com.itheima.reggie.controller
UserController
sendMsg:验证码
```

