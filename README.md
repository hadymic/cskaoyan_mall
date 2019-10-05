# cskaoyan_mall
cskaoyan商城项目

[TOC]

## <span style="color: red">提交格式</span>

```
git commit -m "author:名字 msg:此次提交的主要改动"
```
例：
```
git commit -m "author:hadymic msg:create project"
```

## rule

1. 在dev分支中进行开发，不允许提交至master
2. 自己写的类应注明自己的作者信息及类的用途
例：
```java
/**
 * 后台登录系统
 *
 * @author hadymic
 */
@RestController
public class AuthController {
}
```

3. 每个方法应至少注明用途，多人开发同一类时（如service中的方法，或mapper中的方法），方法还应注明作者信息

4. 方法名应清晰明了，mapper中的方法名规定
    - 增：`insert*`
    - 删：`delete*`
    - 改：`update*`
    - 查：`query*`或`select*`

5. 多写注释

6. mapper中只能将数据库查询到的信息直接返回，业务逻辑应在service中处理

7. 提交前必须测试通过，最好半天提交一次（早提交不用解决冲突）

8. 组员之间多交流，尽量避免代码冗余，不懂的地方互相请教
