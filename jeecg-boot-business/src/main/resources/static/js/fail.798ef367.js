(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["fail"],{"0673":function(t,e,s){"use strict";s.r(e);var c=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"exception"},[s("div",{staticClass:"img"},[s("img",{attrs:{src:t.config[t.type].img}})]),s("div",{staticClass:"content"},[s("h1",[t._v(t._s(t.config[t.type].title))]),s("div",{staticClass:"desc"},[t._v(t._s(t.config[t.type].desc))]),s("div",{staticClass:"action"},[s("a-button",{attrs:{type:"primary"},on:{click:t.handleToHome}},[t._v("返回首页")])],1)])])},i=[],n={403:{img:"https://gw.alipayobjects.com/zos/rmsportal/wZcnGqRDyhPOEYFcZDnb.svg",title:"403",desc:"抱歉，你无权访问该页面"},404:{img:"https://gw.alipayobjects.com/zos/rmsportal/KpnpchXsobRgLElEozzI.svg",title:"404",desc:"抱歉，你访问的页面不存在或仍在开发中"},500:{img:"https://gw.alipayobjects.com/zos/rmsportal/RVRUAYdCGeYNBWoKiIwB.svg",title:"500",desc:"抱歉，服务器出错了"}},a=n,o={name:"Exception",props:{type:{type:String,default:"404"}},data:function(){return{config:a}},methods:{handleToHome:function(){this.$router.push({name:"dashboard"})}}},r=o,l=(s("62d6"),s("17cc")),p=Object(l["a"])(r,c,i,!1,null,"48c3c2d3",null);e["default"]=p.exports},"62d6":function(t,e,s){"use strict";var c=s("7948"),i=s.n(c);i.a},7948:function(t,e,s){},cc89:function(t,e,s){"use strict";s.r(e);var c=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("exception-page",{attrs:{type:"404"}})},i=[],n=s("0673"),a={components:{ExceptionPage:n["default"]}},o=a,r=s("17cc"),l=Object(r["a"])(o,c,i,!1,null,"ec864426",null);e["default"]=l.exports}}]);