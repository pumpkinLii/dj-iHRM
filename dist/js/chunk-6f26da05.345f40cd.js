(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6f26da05"],{"0326":function(t,e,a){},1276:function(t,e,a){"use strict";var n=a("d784"),r=a("44e7"),o=a("825a"),l=a("1d80"),i=a("4840"),c=a("8aa5"),s=a("50c4"),u=a("14c3"),f=a("9263"),m=a("d039"),d=[].push,h=Math.min,p=4294967295,g=!m((function(){return!RegExp(p,"y")}));n("split",2,(function(t,e,a){var n;return n="c"=="abbc".split(/(b)*/)[1]||4!="test".split(/(?:)/,-1).length||2!="ab".split(/(?:ab)*/).length||4!=".".split(/(.?)(.?)/).length||".".split(/()()/).length>1||"".split(/.?/).length?function(t,a){var n=String(l(this)),o=void 0===a?p:a>>>0;if(0===o)return[];if(void 0===t)return[n];if(!r(t))return e.call(n,t,o);var i,c,s,u=[],m=(t.ignoreCase?"i":"")+(t.multiline?"m":"")+(t.unicode?"u":"")+(t.sticky?"y":""),h=0,g=new RegExp(t.source,m+"g");while(i=f.call(g,n)){if(c=g.lastIndex,c>h&&(u.push(n.slice(h,i.index)),i.length>1&&i.index<n.length&&d.apply(u,i.slice(1)),s=i[0].length,h=c,u.length>=o))break;g.lastIndex===i.index&&g.lastIndex++}return h===n.length?!s&&g.test("")||u.push(""):u.push(n.slice(h)),u.length>o?u.slice(0,o):u}:"0".split(void 0,0).length?function(t,a){return void 0===t&&0===a?[]:e.call(this,t,a)}:e,[function(e,a){var r=l(this),o=void 0==e?void 0:e[t];return void 0!==o?o.call(e,r,a):n.call(String(r),e,a)},function(t,r){var l=a(n,t,this,r,n!==e);if(l.done)return l.value;var f=o(t),m=String(this),d=i(f,RegExp),b=f.unicode,v=(f.ignoreCase?"i":"")+(f.multiline?"m":"")+(f.unicode?"u":"")+(g?"y":"g"),y=new d(g?f:"^(?:"+f.source+")",v),C=void 0===r?p:r>>>0;if(0===C)return[];if(0===m.length)return null===u(y,m)?[m]:[];var S=0,A=0,x=[];while(A<m.length){y.lastIndex=g?A:0;var w,_=u(y,g?m:m.slice(A));if(null===_||(w=h(s(y.lastIndex+(g?0:A)),m.length))===S)A=c(m,A,b);else{if(x.push(m.slice(S,A)),x.length===C)return x;for(var N=1;N<=_.length-1;N++)if(x.push(_[N]),x.length===C)return x;A=S=w}}return x.push(m.slice(S)),x}]}),!g)},"4df4":function(t,e,a){"use strict";var n=a("0366"),r=a("7b0b"),o=a("9bdd"),l=a("e95a"),i=a("50c4"),c=a("8418"),s=a("35a1");t.exports=function(t){var e,a,u,f,m,d,h=r(t),p="function"==typeof this?this:Array,g=arguments.length,b=g>1?arguments[1]:void 0,v=void 0!==b,y=s(h),C=0;if(v&&(b=n(b,g>2?arguments[2]:void 0,2)),void 0==y||p==Array&&l(y))for(e=i(h.length),a=new p(e);e>C;C++)d=v?b(h[C],C):h[C],c(a,C,d);else for(f=y.call(h),m=f.next,a=new p;!(u=m.call(f)).done;C++)d=v?o(f,b,[u.value,C],!0):u.value,c(a,C,d);return a.length=C,a}},a15b:function(t,e,a){"use strict";var n=a("23e7"),r=a("44ad"),o=a("fc6a"),l=a("a640"),i=[].join,c=r!=Object,s=l("join",",");n({target:"Array",proto:!0,forced:c||!s},{join:function(t){return i.call(o(this),void 0===t?",":t)}})},a630:function(t,e,a){var n=a("23e7"),r=a("4df4"),o=a("1c7e"),l=!o((function(t){Array.from(t)}));n({target:"Array",stat:!0,forced:l},{from:r})},b85c:function(t,e,a){"use strict";a.d(e,"a",(function(){return o}));a("a4d3"),a("e01a"),a("d3b7"),a("d28b"),a("3ca3"),a("ddb0"),a("fb6a"),a("b0c0"),a("a630");function n(t,e){(null==e||e>t.length)&&(e=t.length);for(var a=0,n=new Array(e);a<e;a++)n[a]=t[a];return n}function r(t,e){if(t){if("string"===typeof t)return n(t,e);var a=Object.prototype.toString.call(t).slice(8,-1);return"Object"===a&&t.constructor&&(a=t.constructor.name),"Map"===a||"Set"===a?Array.from(t):"Arguments"===a||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(a)?n(t,e):void 0}}function o(t,e){var a="undefined"!==typeof Symbol&&t[Symbol.iterator]||t["@@iterator"];if(!a){if(Array.isArray(t)||(a=r(t))||e&&t&&"number"===typeof t.length){a&&(t=a);var n=0,o=function(){};return{s:o,n:function(){return n>=t.length?{done:!0}:{done:!1,value:t[n++]}},e:function(t){throw t},f:o}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var l,i=!0,c=!1;return{s:function(){a=a.call(t)},n:function(){var t=a.next();return i=t.done,t},e:function(t){c=!0,l=t},f:function(){try{i||null==a["return"]||a["return"]()}finally{if(c)throw l}}}}},cb65:function(t,e,a){"use strict";a("0326")},d28b:function(t,e,a){var n=a("746f");n("iterator")},de6c:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("h4",[t._v("人员异动")]),a("el-form",{ref:"form",attrs:{rules:t.rules,model:t.form,"label-width":"180px"}},[a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"管理机构",prop:"manageCom"}},[a("el-cascader",{staticStyle:{width:"100%"},attrs:{options:t.options,"show-all-levels":!1,props:{expandTrigger:"hover",checkStrictly:!0},clearable:""},on:{change:t.changeVal},model:{value:t.form.manageCom,callback:function(e){t.$set(t.form,"manageCom",e)},expression:"form.manageCom"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"团队代码"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:""},on:{change:t.handleBranchAttrChange},model:{value:t.form.branchAttr,callback:function(e){t.$set(t.form,"branchAttr",e)},expression:"form.branchAttr"}},t._l(t.list.branchAttr,(function(e,n){return a("el-option",{key:n,attrs:{label:e.branchAttr,value:e.branchAttr}},[a("span",{staticStyle:{float:"right",color:"#8492a6","font-size":"13px"}},[t._v(t._s(e.name))]),a("span",{staticStyle:{float:"left"}},[t._v(t._s(e.branchAttr))])])})),1)],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"团队名称"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:""},on:{change:t.handleBranchNameChange},model:{value:t.form.branchName,callback:function(e){t.$set(t.form,"branchName",e)},expression:"form.branchName "}},t._l(t.list.branchName,(function(e,n){return a("el-option",{key:n,attrs:{label:e.name,value:e.branchAttr}},[a("span",{staticStyle:{float:"left",color:"#8492a6","font-size":"13px"}},[t._v(t._s(e.branchAttr))]),a("span",{staticStyle:{float:"right"}},[t._v(t._s(e.name))])])})),1)],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"代理人代码"}},[a("el-input",{staticStyle:{width:"100%"},attrs:{type:"text",placeholder:"可选项",clearable:""},model:{value:t.form.agentCode,callback:function(e){t.$set(t.form,"agentCode",e)},expression:"form.agentCode"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"代理人姓名"}},[a("el-input",{staticStyle:{width:"100%"},attrs:{type:"text",placeholder:"可选项",clearable:""},model:{value:t.form.agentName,callback:function(e){t.$set(t.form,"agentName",e)},expression:"form.agentName"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"手机号",prop:"agentPhone"}},[a("el-input",{staticStyle:{width:"100%"},attrs:{type:"text",placeholder:"可选项",clearable:""},model:{value:t.form.agentPhone,callback:function(e){t.$set(t.form,"agentPhone",e)},expression:"form.agentPhone"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"证件号码",prop:"idNo"}},[a("el-input",{staticStyle:{width:"100%"},attrs:{type:"text",placeholder:"可选项",clearable:""},model:{value:t.form.idNo,callback:function(e){t.$set(t.form,"idNo",e)},expression:"form.idNo"}})],1)],1)],1),a("el-row",[a("el-form-item",[a("el-col",{staticStyle:{"text-align":"left","margin-top":"1rem"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.handleQuery}},[t._v("查询")]),a("el-button",{attrs:{type:"success",icon:"el-icon-edit"},on:{click:t.handleOpenChangeDialog}},[t._v("异动")]),a("el-button",{attrs:{type:"secondary",icon:"el-icon-refresh-left"},on:{click:t.resetForm}},[t._v("重置")])],1)],1)],1)],1),a("el-divider"),a("div",[a("el-table",{attrs:{data:t.table,stripe:"",border:"",fit:"",height:"300"},on:{"selection-change":t.handleSelectChange}},[a("el-table-column",{attrs:{type:"selection",width:"40"}}),a("el-table-column",{attrs:{label:"人员工号",prop:"agentCode"}}),a("el-table-column",{attrs:{label:"人员姓名",prop:"agentName"}}),a("el-table-column",{attrs:{label:"分公司",prop:"manageCom2"}}),a("el-table-column",{attrs:{label:"中心支公司",prop:"manageCom3"}}),a("el-table-column",{attrs:{label:"团队代码",prop:"agentGroup"}}),a("el-table-column",{attrs:{label:"团队名称",prop:"branchName"}}),a("el-table-column",{attrs:{label:"入司日期",prop:"employDate"}}),a("el-table-column",{attrs:{label:"人员状态",prop:"agentState"}}),a("el-table-column",{attrs:{label:"合同类型",prop:"contractType"}}),a("el-table-column",{attrs:{label:"职级",prop:"agentGrade"}})],1),a("div",{staticClass:"block",staticStyle:{"text-align":"right","margin-top":"1rem"}},[a("el-pagination",{attrs:{"current-page":t.page.currentPage,"page-sizes":[10,20,50,100,200,500],"page-size":t.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:t.page.totalCount},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1),a("PersonChangeDialog")],1)],1)},r=[],o=a("b85c"),l=a("5530"),i=(a("159b"),a("b64b"),a("61f7")),c=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{title:"人员异动",visible:t.config.dialogFormVisible},on:{"update:visible":function(e){return t.$set(t.config,"dialogFormVisible",e)}}},[a("el-form",{attrs:{model:t.form,"label-width":"130px"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"目标中心支公司"}},[a("el-select",{staticStyle:{width:"80%"},attrs:{placeholder:"目标中心支公司",autocomplete:"off"},on:{change:t.getAgentGroupList},model:{value:t.form.manageCom,callback:function(e){t.$set(t.form,"manageCom",e)},expression:"form.manageCom"}},t._l(t.list.manageComList,(function(e,n){return a("el-option",{key:n,attrs:{label:e.name,value:e.code}},[a("span",{staticStyle:{float:"left",color:"#8492a6","font-size":"13px"}},[t._v(t._s(e.code))]),a("span",{staticStyle:{float:"right"}},[t._v(t._s(e.name))])])})),1)],1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"目标团队"}},[a("el-select",{staticStyle:{width:"80%"},attrs:{placeholder:"目标团队"},on:{change:t.getInformation},model:{value:t.form.agentGroup,callback:function(e){t.$set(t.form,"agentGroup",e)},expression:"form.agentGroup"}},t._l(t.list.agentGroupList,(function(e,n){return a("el-option",{key:n,attrs:{label:e.name,value:e.branchAttr}},[a("span",{staticStyle:{float:"left",color:"#8492a6","font-size":"13px"}},[t._v(t._s(e.branchAttr))]),a("span",{staticStyle:{float:"right"}},[t._v(t._s(e.name))])])})),1)],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"调动日期"}},[a("el-date-picker",{staticStyle:{width:"80%"},attrs:{type:"date",placeholder:"选择日期",disabled:!0,"value-format":"yyyy-MM-dd"},model:{value:t.form.modifyDate,callback:function(e){t.$set(t.form,"modifyDate",e)},expression:"form.modifyDate"}})],1)],1),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"目标团队主管代码"}},[a("el-input",{staticStyle:{width:"80%"},attrs:{autocomplete:"off",disabled:!0},model:{value:t.form.adminCode,callback:function(e){t.$set(t.form,"adminCode",e)},expression:"form.adminCode"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"目标团队主管姓名"}},[a("el-input",{staticStyle:{width:"80%"},attrs:{autocomplete:"off",disabled:!0},model:{value:t.form.adminName,callback:function(e){t.$set(t.form,"adminName",e)},expression:"form.adminName"}})],1)],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.config.dialogFormVisible=!1}}},[t._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:t.changeAgent}},[t._v("确 定")])],1)],1)},s=[],u=(a("a15b"),a("ac1f"),a("1276"),a("e8b8")),f={name:"PersonChangeDialog",data:function(){return{config:{dialogFormVisible:!1},form:{manageCom:"",agentGroup:"",modifyDate:"",adminCode:"",adminName:"",agentCodeList:[],operator:"admin"},list:{manageComList:"",agentGroupList:""}}},mounted:function(){var t=this;this.form.modifyDate=(new Date).toLocaleDateString().split("/").join("-"),this.$bus.$on("OPEN_PERSON_CHANGE_DIALOG",(function(e){t.config.dialogFormVisible=!0,Object(u["c"])().then((function(e){t.list.manageComList=e.list})),t.form.agentCodeList=e}))},beforeDestroy:function(){this.$off("OPEN_PERSON_CHANGE_DIALOG")},methods:{getAgentGroupList:function(){var t=this;Object(u["b"])(this.form.manageCom).then((function(e){t.form.agentGroup="",t.list.agentGroupList=e.list}))},getInformation:function(){var t=this;Object(u["e"])(this.form.agentGroup).then((function(e){t.form.adminCode=e.data.managerId,t.form.adminName=e.data.managerName}))},changeAgent:function(){Object(u["a"])(this.form),this.config.dialogFormVisible=!1}}},m=f,d=a("2877"),h=Object(d["a"])(m,c,s,!1,null,null,null),p=h.exports,g={name:"PersonnelChange",components:{PersonChangeDialog:p},data:function(){return{index2:0,form:{manageCom:"",branchAttr:"",branchName:"",agentCode:"",agentName:"",agentPhone:"",idNo:""},list:{manageCom:[],branchAttr:[],branchName:[]},rules:{manageCom:[{required:!0,message:"请选择管理机构",trigger:"change"}],agentPhone:[{validator:i["j"],trigger:"blur"}]},table:[],selected:[],page:{currentPage:1,totalCount:0,pageSize:10},options:[]}},created:function(){var t=this;Object(u["f"])().then((function(e){t.options.push(e["result"])}))},methods:{handleBranchAttrChange:function(){this.form.branchName=this.form.branchAttr},handleBranchNameChange:function(){this.form.branchAttr=this.form.branchName},changeVal:function(){var t=this;Object(u["b"])(this.form.manageCom).then((function(e){t.list.branchAttr=e.list,t.list.branchName=e.list}))},resetForm:function(){var t=this;this.$refs["form"].resetFields(),Object.keys(this.form).forEach((function(e){t.form[e]=""})),this.table=[],this.page.currentPage=1,this.page.totalCount=0},handleQuery:function(){var t=this;this.$refs["form"].validate((function(e){if(!e)return!1;t.table=[];var a=Object(l["a"])({},t.form);a.manageCom=0!==t.form.manageCom.length?t.form.manageCom[t.form.manageCom.length-1]:"",Object(u["d"])(a,{pageSize:t.page.pageSize,currentPage:t.page.currentPage}).then((function(e){console.log(e),t.table=e["list"],t.page.totalCount=e["totalCount"],t.$message.success("查询完毕")}))}))},handleOpenChangeDialog:function(){this.$bus.$emit("OPEN_PERSON_CHANGE_DIALOG",this.selected)},handleSizeChange:function(t){this.page.pageSize=t,this.handleQuery()},handleCurrentChange:function(t){this.page.currentPage=t,this.handleQuery()},handleSelectChange:function(t){this.selected=[];var e,a=Object(o["a"])(t);try{for(a.s();!(e=a.n()).done;){var n=e.value;this.selected.push(n.agentCode)}}catch(r){a.e(r)}finally{a.f()}}}},b=g,v=(a("cb65"),Object(d["a"])(b,n,r,!1,null,"59cce9eb",null));e["default"]=v.exports},e01a:function(t,e,a){"use strict";var n=a("23e7"),r=a("83ab"),o=a("da84"),l=a("5135"),i=a("861d"),c=a("9bf2").f,s=a("e893"),u=o.Symbol;if(r&&"function"==typeof u&&(!("description"in u.prototype)||void 0!==u().description)){var f={},m=function(){var t=arguments.length<1||void 0===arguments[0]?void 0:String(arguments[0]),e=this instanceof m?new u(t):void 0===t?u():u(t);return""===t&&(f[e]=!0),e};s(m,u);var d=m.prototype=u.prototype;d.constructor=m;var h=d.toString,p="Symbol(test)"==String(u("test")),g=/^Symbol\((.*)\)[^)]+$/;c(d,"description",{configurable:!0,get:function(){var t=i(this)?this.valueOf():this,e=h.call(t);if(l(f,t))return"";var a=p?e.slice(7,-1):e.replace(g,"$1");return""===a?void 0:a}}),n({global:!0,forced:!0},{Symbol:m})}},e8b8:function(t,e,a){"use strict";a.d(e,"d",(function(){return r})),a.d(e,"c",(function(){return o})),a.d(e,"b",(function(){return l})),a.d(e,"e",(function(){return i})),a.d(e,"a",(function(){return c})),a.d(e,"f",(function(){return s}));var n=a("b775");function r(t,e){var a="/search/return",r="?limit="+e.pageSize+"&page="+e.currentPage;return Object(n["a"])({url:a+r,method:"post",data:t})}function o(){return Object(n["a"])({url:"/returnMes/com4Info",method:"post"})}function l(t){var e="/returnMes/groupInfo",a="?manageCode="+t;return Object(n["a"])({url:e+a,method:"post"})}function i(t){var e="/returnMes/managerInfo",a="?branchAttr="+t;return Object(n["a"])({url:e+a,method:"post"})}function c(t){return Object(n["a"])({url:"/agent/change",method:"post",data:t})}function s(){return Object(n["a"])({url:"/get/comresource",method:"post"})}},fb6a:function(t,e,a){"use strict";var n=a("23e7"),r=a("861d"),o=a("e8b5"),l=a("23cb"),i=a("50c4"),c=a("fc6a"),s=a("8418"),u=a("b622"),f=a("1dde"),m=a("ae40"),d=f("slice"),h=m("slice",{ACCESSORS:!0,0:0,1:2}),p=u("species"),g=[].slice,b=Math.max;n({target:"Array",proto:!0,forced:!d||!h},{slice:function(t,e){var a,n,u,f=c(this),m=i(f.length),d=l(t,m),h=l(void 0===e?m:e,m);if(o(f)&&(a=f.constructor,"function"!=typeof a||a!==Array&&!o(a.prototype)?r(a)&&(a=a[p],null===a&&(a=void 0)):a=void 0,a===Array||void 0===a))return g.call(f,d,h);for(n=new(void 0===a?Array:a)(b(h-d,0)),u=0;d<h;d++,u++)d in f&&s(n,u,f[d]);return n.length=u,n}})}}]);