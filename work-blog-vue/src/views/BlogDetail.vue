<template>
  <div>
    <Header></Header>

    <div class="mblog">
      <h2> {{ blog.title }}</h2>
      <el-link icon="el-icon-edit" v-if="ownBlog">
        <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}" >
        编辑
        </router-link>
      </el-link>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>

    </div>

  </div>
</template>

<script>
  import 'github-markdown-css'//导入页面(公共插件)
  import Header from "../components/Header";// 然后添加样式markdown-body

  export default {
    name: "BlogDetail.vue",
    components: {Header},//导入页面
    data() {
      return {
        blog: {
          id: "",
          title: "",
          content: ""
        },
        ownBlog: false
      }
    },
    created() {//回显(编辑时获取id把id下相对于的数据重现显示)
      const blogId = this.$route.params.blogId//获取路由下传递过来的id
      console.log(blogId)
      const _this = this
      this.$axios.get('/blog/' + blogId).then(res => {
        const blog = res.data.data
        _this.blog.id = blog.id
        _this.blog.title = blog.title

        var MardownIt = require("markdown-it")//获取markdown渲染文档工具
        var md = new MardownIt()

        var result = md.render(blog.content)//将值渲染好格式
        _this.blog.content = result
        _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)//编辑按钮于作者id相同时才显示

      })
    }
  }
</script>

<style scoped>
  .mblog {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    width: 100%;
    min-height: 700px;
    padding: 20px 15px;
  }

</style>