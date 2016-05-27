# Spring Boot WebApp Boilerplate

[![Build Status Img]][Build Status]
[![GitHub version Img]][GitHub version]
[![Coverage Status Img]][Coverage Status]


Spring Boot WebApp Boilerplate是一个基于Spring Boot，使用服务器端技术的Web应用模板。

## 技术栈
- Spring Boot 应用配置启动
- Spring MVC Web层框架
- Spring Data JPA 基于DDD的统一持久层
- Hibernate ORM框架
- Thymeleaf 模板语言
- Spring Security 权限管理
- Flyway 数据库移形工具
- Groovy 书写测试脚本
- Spock 基于BDD的测试框架
- Gradle 构建工具
- Docker 容器技术

## 提高开发效率

## 使用单独的开发数据库

使用H2的内存模式作为单独的开发数据库有诸多的好处。

- 开发环境的构建更为简单 不需要依赖外部的数据库
- 可靠的数据环境 避免多人连接同一个数据库的对数据库的修改，同时重启应用即可重新构建数据库环境，能更方便的调试程序
- 方便的修改数据库 可以使用Hibernate的h2ddl来生成数据库，领域模型的修改直接改代码即可

## 修改代码的快速部署

代码修改后的重新编译部署是开发效率低下的主要原因。JVM的热部署技术能够解决一定的问题，但是对于使用Spring框架的情况下，配置文件、注解的修改等热部署
并没有效果，都只能重启服务器。Spring-boot-devtools能够针对类路径中文件的修改进行快速的重启，配合其提供的LiveReload功能，能够快速地反馈修改。

## 提供的功能实现

项目采用基于[DDD]的应用架构。经典的DDD示例应用请参考[这里][dddsample-core]。

目录结构

- application DDD的应用层
- conf Spring基于Java Code的配置文件
- domain DDD的domain层，包括Entity、Repository、ValueObject
- view 视图层扩展
- web Web层

### 

### 实体和值对象

`domain.shared`目录下存在Entity以及ValueObject的接口，需根据DDD的定义实现不同的接口。

#### BaseEntity

实现了Entity接口，并用于封装实体共通的属性行为，通常情况实体继承该类即可。

既有的封装包括

- 实体的主键 默认使用AUTO
- 实体的创建时间和更新时间 添加了自动更新这两个字段的回调方法
- 实现了Entity接口中的方法 根据DDD的定义，实现了`sameIdentityAs`方法以及`toString`、`equals`、`hashCode`等方法


#### 干掉冗余的get/set方法

Lombok提供了一些实用的注解，能够在编译时根据注解来生成代码。比如常用的get/set，toString，equals，hashCode等方法。
IDEA中使用lombok需要安装对应的插件并启用Compiler下的Annotation Processor。

### 权限管理

权限管理通过Spring Security来实现。配置文件为`com.github.yukinami.webapp.conf.SecurityConfig`。

默认使用了Spring Boot提供的配置策略，使用的是基于权限的授权方式，所有资源的访问，用户至少得包含`USER_ROLE`权限。用户表应该至少包括`username`、
`password`、`enabled`这三个字段。

#### 密码加密方式

对于develop环境采用了明文，方便开发时查看和修改。除此以外的环境使用BCrypt进行加密。

### 应用配置

应用的配置文件位于`resources/config`目录下。针对不同profile的配置在对应的文件下。默认的激活的profile为develop，配置在
`application.properties`中。

对于develop profile，默认激活了以下特性

- 显示执行的SQL
- 格式化执行的SQL
- 生成Hibernate Session的统计输出
- 输出统计数据

其中统计数据会显示当前执行的Hibernate Session中各个执行阶段次数时间等等，方面调试性能。


### 数据库

#### 查询数据

通过`/h2-console`路径，可以访问h2提供的基于web界面的管理端界面，它是一个SQL客户端工具，可以查看编辑数据库。

#### 移形

对于开发环境，数据库的变更直接修改模型，然后通过hbm2ddl来创建最新的数据库schema。
对于生产环境，数据库的变更通过flyway脚本来实现。移形脚本的定义位于`resource/db/migration/production`目录。

#### 开发环境种子数据

H2内存数据库创建完schema后需要填充一些种子数据以供程序的运行调试的需要。插入种子数据的脚本是文件`resource/db/migration/develop/import.sql`。

### 静态资源

资源文件位于`resources/static`目录下

#### 资源文件的cache busting

所有资源文件的请求都会自动的加上根据内容进行哈希产生的版本号，修改资源文件后，版本号会发生改变是缓存失效

#### webjars

第三方库可以通过webjars的方式来引入。由于引用`webjars-locator`模板，框架会自动处理webjars中对版本的引用。

例如引用文件`/webjars/bootstrap/3.3.6/js/bootstrap.min.js`时，可以省略中间的版本号，直接这样引用即可
`/webjars/bootstrap/js/bootstrap.min.js`


### 模板

模板使用thymeleaf来实现。

为什么使用thymeleaf

- Thymeleaf本身是一个较为现代的模板引擎，提供了不少很好的特性
- 基于thymeleaf的模板是可以直接当做HTML来预览运行的，这对于集成静态页面是相当友好的
- Spring对它有较好的支持
- 更方便的扩展，并提供了不少对第三方库的集成扩展，例如spring-security等
- 由于它是一个模板引擎，相较于JSP它对测试更为友好

模板文件定义在`resources/templates`目录下。

#### 目录结构

模板文件的目录结构推荐的组织方式是路径和路径映射保持一致。例如对于`/users/index`的映射路径，模板文件定义成`templates/users/index.html`。

#### 模板layout

Layouts文件定义在`resources/templates/layouts`目录下。
默认使用了[html5boilerplate]作为模板的基础layout。Layout的实现使用了[thymeleaf-layout-dialect]库，具体的使用参考文档。

#### Helper方法的扩展

如果希望自定义一些Helper方法供在模板中进行调用，可以参考`com.github.yukinami.webapp.view.helper.UrlHelper`的实现，通过自定义
ApplicationDialect来实现扩展。

### 错误页面

错误页面定义在`resources/templates`目录下，模板名称以${errorCode}.html的格式命名。 如果是多个错误映射到同一个页面，可以通过xxx的方式来匹配。
例如，匹配所有的5xx页面，则定义`resources/templates/5xx.html`即可。

如果要覆盖所有的错误页面，则直接定义文件`resources/templates/error.html`。


### 国际化

国际化内容定义`resources/messages.properties`文件中。即便不需要提供国际化功能，也建议把模板的内容通过国际化文件来定义。这样做的好处是又做了
一层视图的抽象，方面维护和修改。

在thymeleaf的模板文件中通过`#{}`的语法来引用国际化内容的，基于thymeleaf的优点，实现国际化并不会造成模板文件本身的可读性的下降。

### 文件的组织

为了避免混乱，推荐将message文件根据功能模块、以及作用（页面label、页面提示）等将messages文件拆分成多个。

各个messages文件中的key定义规则如下

- 页面label  以view作为前缀，紧跟template的路径，最后添加label的名称。 例如以`view.uers.index.title`来定义用户列表页的标题。


### 分页的实现

Spring Data本身对分页提供了较好的支持，只需要在Controller中用Pageable来接收分页参数，然后传递给Repository接口，即可实现分页查询。
分页的默认页长和排序条件可以通过`@PageableDefault`注解来实现。

分页结果的页面显示，通过基于thymeleaf和Spring Data的`Page`对象的[thymeleaf-spring-data-dialect]来实现，具体参考文档。需要自定义输出
的分页信息中的文字的情况，则通过`resources/thymeleaf-spring-data-dialect/PaginationSummary_zh_CN.properties`文件来实现。

### 测试

使用groovy书写基于spock的BDD测试脚本。

#### Repository集成测试

使用`@DataJpaTest`注解标记Repository的集成测试类文件。

默认也会激活develop profile，加载`import.sql`中的种子数据。为了避免开发环境数据的影响，使用类注解`@ActiveProfiles("test")`来激活
test profile，然后使用`test/resources/db/migration/test/import.sql`来初始化测试环境的种子数据。

NOTE: 这里需要注意的是虽然每个测试类会重新加载ApplicationContext，但是H2数据库并不会重新启动。但是由于加载ApplicationContext的过程中
Hibernate会drop表并重新创建，执行`import.sql`导入种子数据，所以数据库仍然是一个干净的状态。但是如果表本身并不是通过Hibernate创建而是通过
flyway创建的，那就要注意了，表没有被drop重新创建，但是`import.sql`会照样执行，所以可能会存在数据重复的情况。所以建议通过flyway创建表在
`import.sql`中进行插入数据时，最好写DELETE一下该表所有的数据。

##### Data Fixture

为了避免全局数据对测试的影响，测试环境的`import.sql`中应该只包括种子数据。具体的测试数据通过`@Sql`来加载。

NOTE: 如果@Sql中操作的表和`import.sql`中有重复的，建议在@Sql执行的文件中先DELETE一次，避免数据干扰。

#### 页面集成测试

使用Geb进行页面集成测试，使用Geb的原因

- 它基于groovy，可以很好地集成spock
- 基于Page Object Patter，提高测试脚本的复用率
- Geb脚本可以直接拿来用于E2E测试

## 运行

通过命令行执行`./gradlew bootRun`来运行，或者导入IDE后直接运行`SpringBootWebAppBoilerplateApplication.java`文件

## 构建

- `./gradlew build`构建可执行jar包
- `./gradlew buildDocker`构建docker镜像

[DDD]: http://www.codeproject.com/Articles/339725/Domain-Driven-Design-Clear-Your-Concepts-Before-Yo
[dddsample-core]: https://github.com/citerus/dddsample-core
[thymeleaf-spring-data-dialect]: https://github.com/jpenren/thymeleaf-spring-data-dialect
[html5boilerplate]: https://html5boilerplate.com/
[thymeleaf-layout-dialect]: https://github.com/ultraq/thymeleaf-layout-dialect
[Build Status Img]: https://travis-ci.org/yukinami/spring-boot-webapp-boilerplate.svg?branch=master
[Build Status]: https://travis-ci.org/yukinami/spring-boot-webapp-boilerplate
[GitHub version Img]: https://badge.fury.io/gh/yukinami%2Fspring-boot-webapp-boilerplate.svg
[GitHub version]: https://badge.fury.io/gh/yukinami%2Fspring-boot-webapp-boilerplate
[Coverage Status Img]: https://coveralls.io/repos/github/yukinami/spring-boot-webapp-boilerplate/badge.svg?branch=master
[Coverage Status]: https://coveralls.io/github/yukinami/spring-boot-webapp-boilerplate?branch=master

