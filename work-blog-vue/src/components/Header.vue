<template>
  <div class="m-content">
    <h3>欢迎来到WorkCat的博客</h3>
    <div class="block">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <div>{{ user.username }}</div>
    </div>

    <div class="maction">
      <span><el-link href="/blogs">主页</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span><el-link type="success" href="/blog/add">发表博客</el-link></span>

      <el-divider direction="vertical"></el-divider>
      <span v-show="!hasLogin"><el-link type="primary" href="/login">登录</el-link></span>

      <span v-show="hasLogin"><el-link type="danger" @click="logout">退出</el-link></span>
    </div>

  </div>
</template>

<script>
  export default {
    name: "Header",
    data(){
      let hasLogin;
      return{
        user:{
          username:'请先登录',
          avatar:''
        }, hasLogin:false//登录按钮默认为不显示
      }
    },
    methods:{
      /*退出登录*/
      logout(){
        const _this=this
        _this.$axios.get("/logout",{
          headers:{
            "Authorization":localStorage.getItem("token")//获取token信息
          }
        }).then(res =>{
          _this.$store.commit("REMOVE_INFO")//清除登录时的用户信息
          _this.$router.push("/login")
        })
      }
    },
    //页面加载时(回显 拉去userIfon用户信息 从index.js抓取信息)
    created() {
      if(this.$store.getters.getUser.username){//不等空时
        this.user.username=this.$store.getters.getUser.username
        this.user.avatar=this.$store.getters.getUser.avatar

        this.hasLogin=true//
      }
    }
  }
</script>

<style scoped>

  .m-content {
    max-width: 960px;
    margin: 0 auto;
    text-align: center;
  }
  .maction {
    margin: 10px 0;
  }

</style>