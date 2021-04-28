import Vue from 'vue'
import VueRouter from 'vue-router'
import MedicineShow from '../views/MedicineShow'
import AddMedicine from '../views/AddMedicine'
import ProviderShow from '../views/ProviderShow'
import AddProvider from '../views/AddProvider'
import Index from '../views/Index'
import MedicineUpdate from '../views/MedicineUpdate'
import ProviderUpdate from '../views/ProviderUpdate'
import DeleteMenu from '../views/DeleteMenu'
import ExpiredMenu from '../views/ExpiredMenu'
import DrugExpirationReminder from '../views/DrugExpirationReminder'
import Test from '../views/test'
import Login from '../views/front-end/login'
import FrontMedicineShow from '../views/front-end/medicineShow'
import BackGroundShow from '../views/backGroundShow'
import NotFound from '../views/404'
import Demand from '../views/front-end/demand'
import Index2 from '../views/front-end/index2'
import Order from '../views/front-end/order'
import FrontShow from '../views/front-end/frontShow'
import AddDemand from '../views/front-end/addDemand'
import UpdateDemand from '../views/front-end/updateDemand'
import OrderHistory from '../views/front-end/orderHistory'
import OrderSingle from '../views/front-end/orderSingle'
import OrderProcess from '../views/orderProcess'
import OrderUpdate from '../views/orderUpdate'
import OrderSingle2 from '../views/orderSingle'
import DemandShow from '../views/demandShow'
import DrugEntered from '../views/drugEntered'
import DrugEnteredHistory from '../views/drugEnteredHistory'
import DrugEnteredSingle from '../views/drugEnteredSingle'
import DrugRecord from '../views/drugRecord'
Vue.use(VueRouter)

const routes = [
  {
    path:'*',
    name:"notFound",
    component:NotFound
  },

  //前台路由
  {
    path: "/frontShow",
    name: "前台展示",
    component:FrontShow
  },
  {
    path: "/order",
    name: "订单信息",
    component:Order
  },
  {
    path:"/orderOfAll",
    name:"历史订单",
    component:OrderHistory
  },
  {
    path:"/orderSingle",
    name:"历史订单",
    component:OrderSingle
  },
  {
    path: "/medicineFrontShow",
    name: "药品展示",
    component:FrontMedicineShow
  },
  {
    path:"/demand",
    name:"顾客需求管理",
    component:Demand
  },
  {
    path:"/index2",
    name:"notFound",
    component:Index2
  },
  {
    path:'/addDemand',
    name:"添加新需求",
    component:AddDemand
  },
  {
    path:'/updateDemand',
    name:'修改需求',
    component:UpdateDemand
  },
  //登录页
  {
    path:"/",
    name:"login",
    component:Login
  },
  //后台首页
  {
    path:'/back',
    name:"back",
    component:BackGroundShow
  },
  //后台路由
  {
    path:"/medince",
    name:"药品库存管理",
    component:Index,
    show:true, 
    redirect:"/medicineShow",
    children:[
      {
        path: "/medicineShow",
        name: "药品信息",
        component:MedicineShow
      },
      {
        path: "/addMedicine",
        name: "添加药品",
        component:AddMedicine
      },
    ]
  },
  {
    path:"/navigation",
    name:"供应商管理",
    show:true,
    component:Index,
    children:[
      {
        path: "/providerShow",
        name: "供应商信息",
        component:ProviderShow
      },
      {
        path: "/addProvider",
        name: "添加供应商",
        component:AddProvider
      },
    ]
  },   
  {
    path:"/navigation2",
    name:"药品报损记录",
    show:true,
    component:Index,
    children:[
      {
        path: "/deleteMenu",
        name: "手动删除记录",
        component:DeleteMenu
      },
      {
        path: "/expiredMenu",
        name: "药品到期记录",
        component:ExpiredMenu
      },
    ]
  },  
  {
    path:"/navigation3",
    name:"临近药品过期提醒",
    component:Index,
    show:false,
    children:[
      {
        path: "/drugExpirationReminder",
        name: "临近药品过期提醒",
        component:DrugExpirationReminder
      },
    ]
  },
  {
    path:"/navigation4",
    name:"进药管理",
    component:Index,
    show:true,
    children:[
      {
        path: "/demandShow",
        name: "查看顾客需求",
        component:DemandShow
      },
      {
        path: "/drugEntered",
        name: "查看当前进药订单",
        component:DrugEntered
      },
      {
        path: "/drugEnteredHistory",
        name: "查看历史进药订单",
        component:DrugEnteredHistory
      },
    ]
  },
  {
    path:"/navigation5",
    name:"出药管理",
    component:Index,
    show:true,
    children:[
      {
        path: "/orderProcess",
        name: "顾客订单处理",
        component:OrderProcess
      },
      {
        path: "/drugRecord",
        name: "药品销售记录",
        component:DrugRecord
      },
    ]
  },
  {
    path:"/navigation6",
    name:"出药管理",
    component:Index,
    show:false,
    children:[
      {
        path:"/orderUpdate",
        component:OrderUpdate
      },
      {
        path:"/orderSingleBack",
        component:OrderSingle2
      },
      {
        path:"/drugEnteredSingle",
        component:DrugEnteredSingle
      },
    ]
  },

  {
    path:"/medicineUpdate",
    component:MedicineUpdate
  },
  { 
   path:"/providerUpdate",
   component: ProviderUpdate
  },
   {
     path:"/orderUpdate",
     component:OrderUpdate
   },
   {
     path:"/orderSingleBack",
     component:OrderSingle2
   },
];
const routes2=[

  {
    path:"/front1",
    name:"药品展示",
    show:true,
    component:Index2,
    children:[
      {
        path: "/medicineFrontShow",
        name: "药品展示",
        component:FrontMedicineShow
      },
    ]
  }, 
  {
    path:"/navigation2_1",
    name:"顾客需求管理",
    component:Index2,
    show:true, 
    children:[
      {
        path: "/demand",
        name: "顾客需求管理",
        component:Demand
      },
      {
        path: "/addDemand",
        name: "添加新需求",
        component:AddMedicine
      },
    ]
  }, 
  {
    path:"/front3",
    name:"订单管理",
    show:true,
    component:Index2,
    children:[
      {
        path: "/orderOfAll",
        name: "历史订单",
        component:Order
      },
      {
        path: "/order",
        name: "当前订单信息",
        component:Order
      },
    ]
  }, 
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  routes2
})

export default router
