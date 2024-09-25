module.exports = {
    chainWebpack: config =>{
        config.plugin('html')
            .tap(args => {
                args[0].title = "在线考试系统";
                args[0].keywords = "在线考试系统";
                args[0].description = "在线考试系统";
                return args;
            })
    },
};
