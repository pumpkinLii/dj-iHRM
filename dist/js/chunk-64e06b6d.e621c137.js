(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-64e06b6d"],{2017:function(e,t,s){"use strict";s("cafe")},"336c":function(e,t,s){e.exports=s.p+"img/back.2ef9906c.png"},"3e5b":function(e,t,s){"use strict";s("de24")},"9ed6":function(e,t,s){"use strict";s.r(t);var o=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"login-container",style:{backgroundImage:"url("+s("336c")+")"}},[o("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:e.loginForm,rules:e.loginRules,"auto-complete":"on","label-position":"left"}},[o("div",{staticClass:"title-container"},[o("h3",{staticClass:"title"},[e._v("大家保险销售管理系统")])]),o("el-form-item",{attrs:{prop:"username"}},[o("span",{staticClass:"svg-container"},[o("svg-icon",{attrs:{"icon-class":"user"}})],1),o("el-input",{ref:"userId",attrs:{placeholder:"用户ID",name:"userId",type:"text",tabindex:"1","auto-complete":"on"},model:{value:e.loginForm.userId,callback:function(t){e.$set(e.loginForm,"userId",t)},expression:"loginForm.userId"}})],1),o("el-form-item",[o("span",{staticClass:"svg-container"},[o("svg-icon",{attrs:{"icon-class":"password"}})],1),o("el-input",{ref:"password",attrs:{placeholder:"密码",type:e.passwordType,name:"password",tabindex:"2","auto-complete":"on"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleLogin(t)}},model:{value:e.loginForm.password,callback:function(t){e.$set(e.loginForm,"password",t)},expression:"loginForm.password"}}),o("span",{staticClass:"show-pwd",on:{click:e.showPwd}},[o("svg-icon",{attrs:{"icon-class":"password"===e.passwordType?"eye":"eye-open"}})],1)],1),o("el-button",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{loading:e.loading,type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),e.handleLogin(t)}}},[e._v("登录")])],1)],1)},r=[],n=(s("498a"),{name:"Login",data:function(){var e=function(e,t,s){0===t.trim().length?s(new Error("请输入用户ID")):s()},t=function(e,t,s){t.length<6?s(new Error("密码长度不可小于6位")):s()};return{loginForm:{userId:"",password:""},loginRules:{userId:[{required:!0,trigger:"blur",validator:e}],password:[{required:!0,trigger:"blur",validator:t}]},loading:!1,passwordType:"password",redirect:void 0}},watch:{$route:{handler:function(e){this.redirect=e.query&&e.query.redirect},immediate:!0}},methods:{showPwd:function(){var e=this;"password"===this.passwordType?this.passwordType="":this.passwordType="password",this.$nextTick((function(){e.$refs.password.focus()}))},handleLogin:function(){var e=this;this.$refs.loginForm.validate((function(t){if(!t)return e.$message.error("请检查信息是否有误"),!1;e.loading=!0;var s={userId:e.loginForm.userId,userPassword:e.loginForm.password};e.$store.dispatch("user/login",s).then((function(){e.$router.push({path:e.redirect||"/dashboard"}),e.loading=!1})).catch((function(){e.loading=!1}))}))}}}),i=n,a=(s("2017"),s("3e5b"),s("2877")),l=Object(a["a"])(i,o,r,!1,null,"238d8025",null);t["default"]=l.exports},cafe:function(e,t,s){},de24:function(e,t,s){}}]);