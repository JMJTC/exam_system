# 在线考试系统

## 项目简介
这是一个基于Spring Boot + Vue.js开发的在线考试系统，提供了用户注册登录、考试管理、试题管理、成绩管理等功能，适用于各类教育机构和企业内部培训考试场景。

## 技术栈

### 后端技术
- Spring Boot 2.5.6
- MyBatis-Plus 3.5.6
- MySQL 8.0.31
- Swagger 2.9.2 (API文档)
- JWT 0.9.1 (身份认证)
- Hutool 5.7.20 (工具类库)
- Lombok (减少样板代码)
- Freemarker 2.3.30 (模板引擎)

### 前端技术
- Vue.js 2.6.11
- Vue Router 3.2.0
- Element UI 2.15.9
- Axios 0.27.2
- WangEditor 4.7.15 (富文本编辑器)

## 项目结构

### 后端结构
```
src/main/java/com/project/template/
├── TemplateManagement.java       # 项目入口类
├── common/                       # 通用类
├── config/                       # 配置类
├── controller/                   # 控制器层
├── dto/                          # 数据传输对象
├── entity/                       # 实体类
├── enums/                        # 枚举类
├── exception/                    # 异常处理
├── interceptor/                  # 拦截器
├── mapper/                       # 数据访问层
├── service/                      # 业务逻辑层
├── utils/                        # 工具类
├── vo/                           # 视图对象
└── uml/                          # UML设计图
```

### 前端结构
```
web/src/
├── App.vue                       # 根组件
├── assets/                       # 静态资源
├── components/                   # 公共组件
├── main.js                       # 入口文件
├── mixins/                       # 混入
├── router/                       # 路由配置
├── utils/                        # 工具类
└── views/                        # 视图组件
    ├── admin/                    # 管理员页面
    ├── front/                    # 前台页面
    └── layout/                   # 布局组件
```

## 功能模块

### 1. 用户管理
- 用户注册与登录
- 个人信息管理
- 密码修改
- 用户角色权限控制

### 2. 考试管理
- 考试创建与编辑
- 考试列表展示
- 考试状态管理

### 3. 试题管理
- 试题创建与编辑
- 试题分类管理
- 试题列表展示

### 4. 成绩管理
- 考试成绩自动计算
- 成绩查询与统计
- 成绩分析

### 5. 系统管理
- 公告管理
- 轮播图管理
- 链接管理
- 新闻管理
- 文件上传下载

## 数据库设计
系统采用MySQL数据库，主要包含以下表：
- `sys_user`：用户表
- `sys_role`：角色表
- `exam`：考试表
- `exam_question`：试题表
- `score`：成绩表
- `notice`：公告表
- `banner`：轮播图表
- `link`：链接表
- `news`：新闻表

## 快速开始

### 环境要求
- JDK 1.8
- Maven 3.6+
- MySQL 5.7+
- Node.js 12+

### 后端部署
1. 克隆项目代码
2. 导入MySQL数据库（sql/template.sql）
3. 配置application.properties中的数据库连接信息
4. 使用Maven构建项目：`mvn clean package`
5. 运行jar包：`java -jar target/template-0.0.1-SNAPSHOT.jar`

### 前端部署
1. 进入web目录：`cd web`
2. 安装依赖：`npm install`
3. 开发环境运行：`npm run serve-dev`
4. 生产环境构建：`npm run build-prod`

## API文档
项目集成了Swagger，启动后可访问以下地址查看API文档：
- Swagger UI：http://localhost:8080/swagger-ui.html
- Swagger Bootstrap UI：http://localhost:8080/doc.html

## 系统访问
- 前端访问地址：http://localhost:8080
- 管理员账号：admin/123456
- 普通用户账号：user/123456

## 项目特色
1. 前后端分离架构，易于维护和扩展
2. 完整的用户权限管理系统
3. 丰富的考试管理功能
4. 自动化的成绩计算与统计
5. 友好的用户界面和交互体验

## 注意事项
1. 开发环境配置在.env.dev文件中
2. 生产环境配置在.env.prod文件中
3. 系统默认端口为8080
4. 文件上传大小限制为10MB

## License
MIT