(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3102bc28"],{"0955":function(t,e,a){},"0a51":function(t,e,a){"use strict";var r=a("1c8b"),n=a("1e2c"),o=a("d890"),i=a("faa8"),l=a("a719"),c=a("d910").f,s=a("c69d"),u=o.Symbol;if(n&&"function"==typeof u&&(!("description"in u.prototype)||void 0!==u().description)){var f={},d=function(){var t=arguments.length<1||void 0===arguments[0]?void 0:String(arguments[0]),e=this instanceof d?new u(t):void 0===t?u():u(t);return""===t&&(f[e]=!0),e};s(d,u);var p=d.prototype=u.prototype;p.constructor=d;var m=p.toString,h="Symbol(test)"==String(u("test")),g=/^Symbol\((.*)\)[^)]+$/;c(p,"description",{configurable:!0,get:function(){var t=l(this)?this.valueOf():this,e=m.call(t);if(i(f,t))return"";var a=h?e.slice(7,-1):e.replace(g,"$1");return""===a?void 0:a}}),r({global:!0,forced:!0},{Symbol:d})}},"30d5":function(t,e,a){"use strict";a.d(e,"a",(function(){return o}));a("f3dd"),a("0a51"),a("e18c"),a("9b11"),a("96db"),a("af86"),a("77ad"),a("053b"),a("98e0");function r(t,e){(null==e||e>t.length)&&(e=t.length);for(var a=0,r=new Array(e);a<e;a++)r[a]=t[a];return r}function n(t,e){if(t){if("string"===typeof t)return r(t,e);var a=Object.prototype.toString.call(t).slice(8,-1);return"Object"===a&&t.constructor&&(a=t.constructor.name),"Map"===a||"Set"===a?Array.from(t):"Arguments"===a||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(a)?r(t,e):void 0}}function o(t,e){var a="undefined"!==typeof Symbol&&t[Symbol.iterator]||t["@@iterator"];if(!a){if(Array.isArray(t)||(a=n(t))||e&&t&&"number"===typeof t.length){a&&(t=a);var r=0,o=function(){};return{s:o,n:function(){return r>=t.length?{done:!0}:{done:!1,value:t[r++]}},e:function(t){throw t},f:o}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var i,l=!0,c=!1;return{s:function(){a=a.call(t)},n:function(){var t=a.next();return l=t.done,t},e:function(t){c=!0,i=t},f:function(){try{l||null==a["return"]||a["return"]()}finally{if(c)throw i}}}}},"47d5":function(t,e,a){"use strict";a.d(e,"a",(function(){return n})),a.d(e,"b",(function(){return o}));var r=a("b775");function n(){return Object(r["a"])({url:"/start/get",method:"post"})}function o(){return Object(r["a"])({url:"/get/managecom",method:"post"})}},"69c5":function(t,e,a){var r=a("857c");t.exports=function(t,e,a,n){try{return n?e(r(a)[0],a[1]):e(a)}catch(i){var o=t["return"];throw void 0!==o&&r(o.call(t)),i}}},"77ad":function(t,e,a){"use strict";var r=a("1c8b"),n=a("a719"),o=a("74e7"),i=a("e1d6"),l=a("d88d"),c=a("da10"),s=a("1bbd"),u=a("90fb"),f=a("1ea7"),d=a("ff9c"),p=f("slice"),m=d("slice",{ACCESSORS:!0,0:0,1:2}),h=u("species"),g=[].slice,b=Math.max;r({target:"Array",proto:!0,forced:!p||!m},{slice:function(t,e){var a,r,u,f=c(this),d=l(f.length),p=i(t,d),m=i(void 0===e?d:e,d);if(o(f)&&(a=f.constructor,"function"!=typeof a||a!==Array&&!o(a.prototype)?n(a)&&(a=a[h],null===a&&(a=void 0)):a=void 0,a===Array||void 0===a))return g.call(f,p,m);for(r=new(void 0===a?Array:a)(b(m-p,0)),u=0;p<m;p++,u++)p in f&&s(r,u,f[p]);return r.length=u,r}})},"95dd":function(t,e,a){"use strict";a.r(e);var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("h4",[t._v("离司确认")]),a("el-form",{ref:"form",attrs:{model:t.form,rules:t.rules,"label-width":"180px"}},[a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"管理机构",prop:"manageCom"}},[a("el-cascader",{ref:"elcascader",staticStyle:{width:"100%"},attrs:{options:t.options,"show-all-levels":!1,props:{expandTrigger:"hover",checkStrictly:!0},clearable:""},on:{change:t.changeVal,blur:t.changeValBind},model:{value:t.form.manageCom,callback:function(e){t.$set(t.form,"manageCom",e)},expression:"form.manageCom"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"团队代码",prop:"agentGroup"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:""},model:{value:t.form.agentGroup,callback:function(e){t.$set(t.form,"agentGroup",e)},expression:"form.agentGroup"}},t._l(t.branchAttr,(function(e,r){return a("el-option",{key:r,attrs:{label:e.branchAttr,value:e.branchAttr}},[a("span",{staticStyle:{float:"right",color:"#8492a6","font-size":"13px"}},[t._v(t._s(e.name))]),a("span",{staticStyle:{float:"left"}},[t._v(t._s(e.branchAttr))])])})),1)],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"团队名称",prop:"branchName"}},[a("el-input",{attrs:{placeholder:"请输入团队名称",clearable:""},model:{value:t.form.branchName,callback:function(e){t.$set(t.form,"branchName",e)},expression:"form.branchName"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"人员代码",prop:"agentCode"}},[a("el-input",{attrs:{placeholder:"请输入代理人代码",clearable:""},model:{value:t.form.agentCode,callback:function(e){t.$set(t.form,"agentCode",e)},expression:"form.agentCode"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"人员姓名",prop:"agentName"}},[a("el-input",{attrs:{placeholder:"请输入代理人姓名",clearable:""},model:{value:t.form.agentName,callback:function(e){t.$set(t.form,"agentName",e)},expression:"form.agentName"}})],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"职级",prop:"agentGrade"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:""},model:{value:t.form.agentGrade,callback:function(e){t.$set(t.form,"agentGrade",e)},expression:"form.agentGrade"}},[a("el-option",{attrs:{label:"合同制",value:"0"}})],1)],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"审核状态",prop:"agydepart"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:""},model:{value:t.form.agydepart,callback:function(e){t.$set(t.form,"agydepart",e)},expression:"form.agydepart"}},t._l(t.AgydepartList,(function(e,r){return a("el-option",{key:r,attrs:{value:e.value,label:e.label}},[a("span",{staticStyle:{float:"left",color:"#8492a6","font-size":"13px"}},[t._v(t._s(e.value))]),a("span",{staticStyle:{float:"right"}},[t._v(t._s(e.label))])])})),1)],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"解约原因",prop:"diffCause"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:""},model:{value:t.form.diffCause,callback:function(e){t.$set(t.form,"diffCause",e)},expression:"form.diffCause"}},t._l(t.DiffCauseList,(function(e,r){return a("el-option",{key:r,attrs:{value:e.value,label:e.label}},[a("span",{staticStyle:{float:"left",color:"#8492a6","font-size":"13px"}},[t._v(t._s(e.value))]),a("span",{staticStyle:{float:"right"}},[t._v(t._s(e.label))])])})),1)],1)],1),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"解约日期起期",prop:"diffStartDate"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{"value-format":"yyyy-MM-dd",type:"date",placeholder:"选择日期"},on:{change:t.dateCheck},model:{value:t.form.diffStartDate,callback:function(e){t.$set(t.form,"diffStartDate",e)},expression:"form.diffStartDate"}})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"解约日期止期",prop:"diffEndDate"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{"value-format":"yyyy-MM-dd",type:"date",placeholder:"选择日期"},on:{change:t.dateCheck},model:{value:t.form.diffEndDate,callback:function(e){t.$set(t.form,"diffEndDate",e)},expression:"form.diffEndDate"}})],1)],1)],1),a("el-row",[a("el-form-item",[a("el-col",{staticStyle:{"text-align":"left","margin-top":"1rem"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(e){t.page.currentPage=1,t.handleQuery()}}},[t._v("查询")]),a("el-button",{attrs:{type:"primary"},on:{click:t.check}},[t._v("审核通过")]),a("el-button",{attrs:{type:"primary"},on:{click:t.nocheck}},[t._v("审核不通过")]),a("el-button",{attrs:{type:"secondary",icon:"el-icon-refresh-left"},on:{click:t.resetForm}},[t._v("重置")])],1)],1)],1)],1),a("el-divider"),a("div",[a("el-table",{attrs:{data:t.table,stripe:"",border:"",fit:"",height:"300"},on:{"selection-change":t.handleSelectChange}},[a("el-table-column",{attrs:{type:"selection",width:"40",selectable:t.selectEnable}}),a("el-table-column",{attrs:{label:"人员代码",prop:"agentCode"}}),a("el-table-column",{attrs:{label:"人员姓名",prop:"agentName"}}),a("el-table-column",{attrs:{label:"管理机构代码",prop:"comCode4"}}),a("el-table-column",{attrs:{label:"管理机构名称",prop:"manageCom4"}}),a("el-table-column",{attrs:{label:"团队代码",prop:"agentGroup"}}),a("el-table-column",{attrs:{label:"团队名称",prop:"branchName"}}),a("el-table-column",{attrs:{label:"职级",prop:"agentGradeName"}}),a("el-table-column",{attrs:{label:"解约日期",prop:"diffDate"}}),a("el-table-column",{attrs:{label:"解约原因",prop:"diffCauseName"}}),a("el-table-column",{attrs:{label:"说明",prop:"illustrate"}}),a("el-table-column",{attrs:{label:"审核状态",prop:"agentStateName",width:"110",sortable:""}})],1),a("div",{staticClass:"block",staticStyle:{"text-align":"right","margin-top":"1rem"}},[a("el-pagination",{attrs:{"current-page":t.page.currentPage,"page-sizes":[10,20,50,100,200,500],"page-size":t.page.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:t.page.totalCount},on:{"update:currentPage":function(e){return t.$set(t.page,"currentPage",e)},"update:current-page":function(e){return t.$set(t.page,"currentPage",e)},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)],1)},n=[],o=a("30d5"),i=a("7e2d"),l=(a("08ba"),a("fe8a"),a("47d5")),c=a("bbbc"),s=a("f713"),u=a("e8b8"),f=a("b775");function d(t,e){return Object(f["a"])({url:"/regisnate/approval?num="+e,method:"post",data:t})}var p={name:"LeaveCompany",data:function(){return{form:{manageCom:"",agentGroup:"",branchName:"",agentCode:"",agentName:"",agentGrade:"",agydepart:"",diffCause:"",diffStartDate:"",diffEndDate:""},branchAttr:[],branchName:[],AgydepartList:[],DiffCauseList:[],rules:{},page:{currentPage:1,totalCount:0,pageSize:10},options:[],table:[],selected:[],list:{agentCodeList:[],manageComList:[],agentGroupList:[],agentStateList:[],agydepartStateList:[],reasonList:[]}}},created:function(){var t=this;this.getInitOptions(),c["e"]().then((function(e){t.options.push(e["result"])}))},mounted:function(){var t=this;this.$bus.$on("refreshAgent",(function(){t.hello()})),setInterval((function(){document.querySelectorAll(".el-cascader-node__label").forEach((function(t){t.onclick=function(){this.previousElementSibling&&this.previousElementSibling.click()}}))}),1e3)},beforeDestroy:function(){this.$bus.$off("refreshAgent")},methods:{resetForm:function(){this.$refs["form"].resetFields(),this.table=[]},selectEnable:function(t){return"2"===t["agentStateCom"]},handleQuery:function(){var t=this;this.table=[];var e=Object(i["a"])({},this.form);e.manageCom=0!==this.form.manageCom.length?this.form.manageCom[this.form.manageCom.length-1]:"",s["a"](e,{pageSize:this.page.pageSize,currentPage:this.page.currentPage}).then((function(e){t.table=e["list"],t.page.totalCount=e["totalCount"],t.$message.success("查询完毕")})).catch((function(e){t.page.totalCount=0}))},changeVal:function(){var t,e=this;clearTimeout(t),t=setTimeout((function(){e.$refs.elcascader.dropDownVisible=!1}),300),this.form.agentGroup=""},changeValBind:function(){var t=this;Object(u["b"])(this.form.manageCom).then((function(e){t.branchAttr=e["list"]}))},dateCheck:function(){0!==this.form.diffStartDate.length&&0!==this.form.diffEndDate.length&&this.form.diffStartDate>this.form.diffEndDate&&(this.$message.error("终止日期不能小于起始时期"),this.form.diffEndDate="")},getInitOptions:function(){var t=this;l["a"]().then((function(e){t.codes=e["resource"],t.fillCodes()}))},fillCodes:function(){this.codes?(this.setCodes("agydepartstate",this.AgydepartList),this.setCodes("departreason",this.DiffCauseList)):this.$message.error("未能正确获取下拉菜单")},setCodes:function(t,e){var a=this;this.codes[t]&&Object.keys(this.codes[t]).forEach((function(r){e.push({label:a.codes[t][r],value:r})}))},handleSizeChange:function(t){this.page.pageSize=t,this.handleQuery()},handleCurrentChange:function(t){this.page.currentPage=t,this.handleQuery()},LeaveAddDialog:function(t){this.$bus.$emit("MODIFY_DIALOG",t)},handleSelectChange:function(t){var e=this;Object.keys(this.list).forEach((function(t){e.list[t]=[]}));var a,r=Object(o["a"])(t);try{for(r.s();!(a=r.n()).done;){var n=a.value;this.list.agentCodeList.push(n.agentCode),this.list.manageComList.push(n.comCode4),this.list.agentGroupList.push(n.agentGroup),this.list.agentStateList.push(n["agentGradeCom"]),this.list.agydepartStateList.push(n["agentStateCom"]),this.list.reasonList.push(n["diffCauseCom"])}}catch(i){r.e(i)}finally{r.f()}},check:function(){var t=this,e=1;d(this.list,e).then((function(e){t.$message.success(e["msg"])})).then((function(){t.handleQuery()}))},nocheck:function(){var t=this,e=0;d(this.list,e).then((function(e){t.$message.success(e["msg"])})).then((function(){t.handleQuery()}))}}},m=p,h=(a("fa3c"),a("5d22")),g=Object(h["a"])(m,r,n,!1,null,"08635604",null);e["default"]=g.exports},"98a9":function(t,e,a){var r=a("90fb"),n=a("9806"),o=r("iterator"),i=Array.prototype;t.exports=function(t){return void 0!==t&&(n.Array===t||i[o]===t)}},"98e0":function(t,e,a){var r=a("1c8b"),n=a("e62b"),o=a("f471"),i=!o((function(t){Array.from(t)}));r({target:"Array",stat:!0,forced:i},{from:n})},"9b11":function(t,e,a){var r=a("6d51");r("iterator")},b60f:function(t,e,a){var r=a("2a91"),n=a("9806"),o=a("90fb"),i=o("iterator");t.exports=function(t){if(void 0!=t)return t[i]||t["@@iterator"]||n[r(t)]}},bbbc:function(t,e,a){"use strict";a.d(e,"b",(function(){return n})),a.d(e,"d",(function(){return o})),a.d(e,"c",(function(){return i})),a.d(e,"f",(function(){return l})),a.d(e,"a",(function(){return c})),a.d(e,"e",(function(){return s})),a.d(e,"g",(function(){return u}));var r=a("b775");function n(){return Object(r["a"])({url:"/get/grade",method:"post"})}function o(){return Object(r["a"])({url:"/get/managecom",method:"post"})}function i(t){return Object(r["a"])({url:"/get/gradeteam",method:"post",data:t})}function l(t){return Object(r["a"])({url:"/rank/alter",method:"post",data:t})}function c(t,e){var a="/test/GradeQuery",n="?limit="+e.pageSize+"&page="+e.currentPage;return Object(r["a"])({url:a+n,method:"post",data:t})}function s(){return Object(r["a"])({url:"/get/comresource",method:"post"})}function u(){return Object(r["a"])({url:"/get/grade",method:"post"})}},e62b:function(t,e,a){"use strict";var r=a("e349"),n=a("3553"),o=a("69c5"),i=a("98a9"),l=a("d88d"),c=a("1bbd"),s=a("b60f");t.exports=function(t){var e,a,u,f,d,p,m=n(t),h="function"==typeof this?this:Array,g=arguments.length,b=g>1?arguments[1]:void 0,v=void 0!==b,y=s(m),C=0;if(v&&(b=r(b,g>2?arguments[2]:void 0,2)),void 0==y||h==Array&&i(y))for(e=l(m.length),a=new h(e);e>C;C++)p=v?b(m[C],C):m[C],c(a,C,p);else for(f=y.call(m),d=f.next,a=new h;!(u=d.call(f)).done;C++)p=v?o(f,b,[u.value,C],!0):u.value,c(a,C,p);return a.length=C,a}},e8b8:function(t,e,a){"use strict";a.d(e,"d",(function(){return n})),a.d(e,"c",(function(){return o})),a.d(e,"b",(function(){return i})),a.d(e,"e",(function(){return l})),a.d(e,"a",(function(){return c})),a.d(e,"f",(function(){return s}));var r=a("b775");function n(t,e){var a="/search/return",n="?limit="+e.pageSize+"&page="+e.currentPage;return Object(r["a"])({url:a+n,method:"post",data:t})}function o(){return Object(r["a"])({url:"/returnMes/com4Info",method:"post"})}function i(t){var e="/returnMes/groupInfo",a="?manageCode="+t;return Object(r["a"])({url:e+a,method:"post"})}function l(t){var e="/returnMes/managerInfo",a="?branchAttr="+t;return Object(r["a"])({url:e+a,method:"post"})}function c(t){return Object(r["a"])({url:"/agent/change",method:"post",data:t})}function s(){return Object(r["a"])({url:"/get/comresource",method:"post"})}},f471:function(t,e,a){var r=a("90fb"),n=r("iterator"),o=!1;try{var i=0,l={next:function(){return{done:!!i++}},return:function(){o=!0}};l[n]=function(){return this},Array.from(l,(function(){throw 2}))}catch(c){}t.exports=function(t,e){if(!e&&!o)return!1;var a=!1;try{var r={};r[n]=function(){return{next:function(){return{done:a=!0}}}},t(r)}catch(c){}return a}},f713:function(t,e,a){"use strict";a.d(e,"a",(function(){return n})),a.d(e,"c",(function(){return o})),a.d(e,"b",(function(){return i})),a.d(e,"e",(function(){return l})),a.d(e,"d",(function(){return c}));var r=a("b775");function n(t,e){var a="/dismiss/query",n="?limit="+e.pageSize+"&page="+e.currentPage;return Object(r["a"])({url:a+n,method:"post",data:t})}function o(t){return Object(r["a"])({url:"/dimission/audit",method:"post",data:t})}function i(t){return Object(r["a"])({url:"/Dimission/Dimission",method:"post",data:t})}function l(t){return Object(r["a"])({url:"/depart/Modify",method:"post",data:t})}function c(t){return Object(r["a"])({url:"/insertDimission/insertDimission",method:"post",data:t})}},fa3c:function(t,e,a){"use strict";a("0955")}}]);