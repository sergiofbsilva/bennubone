/*! backbone.paginator - v0.5 - 1/8/2013
 * http://github.com/addyosmani/backbone.paginator
 * Copyright (c) 2013 Addy Osmani; Licensed MIT */
Backbone.Paginator=function(e,t,n){"use strict";var r={};return r.version="0.5",r.clientPager=e.Collection.extend({useDiacriticsPlugin:!0,useLevenshteinPlugin:!0,sortColumn:"",sortDirection:"desc",lastSortColumn:"",fieldFilterRules:[],lastFieldFilterRules:[],filterFields:"",filterExpression:"",lastFilterExpression:"",defaults_ui:{firstPage:0,currentPage:1,perPage:5,totalPages:10,pagesInRange:4},initialize:function(){this.on("add",this.addModel,this),this.on("remove",this.removeModel,this),this.setDefaults()},setDefaults:function(){var e=t.defaults(this.paginator_ui,this.defaults_ui);t.defaults(this,e)},addModel:function(e){this.origModels.push(e)},removeModel:function(e){var n=t.indexOf(this.origModels,e);this.origModels.splice(n,1)},sync:function(e,r,i){var s=this;this.setDefaults();var o={};t.each(t.result(s,"server_api"),function(e,n){t.isFunction(e)&&(e=t.bind(e,s),e=e()),o[n]=e});var u=t.clone(s.paginator_core);t.each(u,function(e,n){t.isFunction(e)&&(e=t.bind(e,s),e=e()),u[n]=e}),u=t.defaults(u,{timeout:25e3,cache:!1,type:"GET",dataType:"jsonp"});var a=i.success;i.success=function(e,t,n){a&&a(e,t,n),r&&r.trigger&&r.trigger("sync",r,e,i)};var f=i.error;i.error=function(e,t,n){f&&f(r,e,i),r&&r.trigger&&r.trigger("error",r,e,i)},u=t.extend(u,{data:decodeURIComponent(n.param(o)),processData:!1,url:t.result(u,"url")},i);var l=n.ajax(u);return r&&r.trigger&&r.trigger("request",r,l,i),l},nextPage:function(){this.currentPage<this.information.totalPages&&(this.currentPage=++this.currentPage,this.pager())},previousPage:function(){this.currentPage=--this.currentPage||1,this.pager()},goTo:function(e){e!==undefined&&(this.currentPage=parseInt(e,10),this.pager())},howManyPer:function(e){if(e!==undefined){var t=this.perPage;this.perPage=parseInt(e,10),this.currentPage=Math.ceil((t*(this.currentPage-1)+1)/e),this.pager()}},setSort:function(e,t){e!==undefined&&t!==undefined&&(this.lastSortColumn=this.sortColumn,this.sortColumn=e,this.sortDirection=t,this.pager(),this.info())},setFieldFilter:function(e){t.isEmpty(e)?(this.lastFieldFilterRules=this.fieldFilterRules,this.fieldFilterRules="",this.pager(),this.info()):(this.lastFieldFilterRules=this.fieldFilterRules,this.fieldFilterRules=e,this.pager(),this.info())},doFakeFieldFilter:function(e){if(!t.isEmpty(e)){var n=this.origModels;return n===undefined&&(n=this.models),n=this._fieldFilter(n,e),this.filterExpression!==""&&(n=this._filter(n,this.filterFields,this.filterExpression)),n.length}},setFilter:function(e,t){e!==undefined&&t!==undefined&&(this.filterFields=e,this.lastFilterExpression=this.filterExpression,this.filterExpression=t,this.pager(),this.info())},doFakeFilter:function(e,n){if(e!==undefined&&n!==undefined){var r=this.origModels;return r===undefined&&(r=this.models),t.isEmpty(this.fieldFilterRules)||(r=this._fieldFilter(r,this.fieldFilterRules)),r=this._filter(r,e,n),r.length}},pager:function(){var e=this,n=this.perPage,r=(e.currentPage-1)*n,i=r+n;e.origModels===undefined&&(e.origModels=e.models),e.models=e.origModels,this.sortColumn!==""&&(e.models=e._sort(e.models,this.sortColumn,this.sortDirection)),t.isEmpty(this.fieldFilterRules)||(e.models=e._fieldFilter(e.models,this.fieldFilterRules)),this.filterExpression!==""&&(e.models=e._filter(e.models,this.filterFields,this.filterExpression));if(this.lastSortColumn!==this.sortColumn||this.lastFilterExpression!==this.filterExpression||!t.isEqual(this.fieldFilterRules,this.lastFieldFilterRules))r=0,i=r+n,e.currentPage=1,this.lastSortColumn=this.sortColumn,this.lastFieldFilterRules=this.fieldFilterRules,this.lastFilterExpression=this.filterExpression;e.sortedAndFilteredModels=e.models,e.reset(e.models.slice(r,i))},_sort:function(e,n,r){return e=e.sort(function(e,i){var s=e.get(n),o=i.get(n);if(t.isUndefined(s)||t.isUndefined(o))return 0;s=s.toString().toLowerCase(),o=o.toString().toLowerCase();if(r==="desc")if(!s.match(/[^\-\d\.]/)&&s.match(/-?[\d\.]*/)&&!o.match(/[^\-\d\.]/)&&o.match(/-?[\d\.]*/)){if(s-0<o-0)return 1;if(s-0>o-0)return-1}else{if(s<o)return 1;if(s>o)return-1}else if(!s.match(/[^\-\d\.]/)&&s.match(/-?[\d\.]*/)&&!o.match(/[^\-\d\.]/)&&o.match(/-?[\d\.]*/)){if(s-0<o-0)return-1;if(s-0>o-0)return 1}else{if(s<o)return-1;if(s>o)return 1}return 0}),e},_fieldFilter:function(e,n){if(t.isEmpty(n))return e;var r=[];return t.each(e,function(e){var i=!0;t.each(n,function(n){if(!i)return!1;i=!1;if(n.type==="function"){var r=t.wrap(n.value,function(t){return t(e.get(n.field))});r()&&(i=!0)}else n.type==="required"?t.isEmpty(e.get(n.field).toString())||(i=!0):n.type==="min"?!t.isNaN(Number(e.get(n.field)))&&!t.isNaN(Number(n.value))&&Number(e.get(n.field))>=Number(n.value)&&(i=!0):n.type==="max"?!t.isNaN(Number(e.get(n.field)))&&!t.isNaN(Number(n.value))&&Number(e.get(n.field))<=Number(n.value)&&(i=!0):n.type==="range"?!t.isNaN(Number(e.get(n.field)))&&t.isObject(n.value)&&!t.isNaN(Number(n.value.min))&&!t.isNaN(Number(n.value.max))&&Number(e.get(n.field))>=Number(n.value.min)&&Number(e.get(n.field))<=Number(n.value.max)&&(i=!0):n.type==="minLength"?e.get(n.field).toString().length>=n.value&&(i=!0):n.type==="maxLength"?e.get(n.field).toString().length<=n.value&&(i=!0):n.type==="rangeLength"?t.isObject(n.value)&&!t.isNaN(Number(n.value.min))&&!t.isNaN(Number(n.value.max))&&e.get(n.field).toString().length>=n.value.min&&e.get(n.field).toString().length<=n.value.max&&(i=!0):n.type==="oneOf"?t.isArray(n.value)&&t.include(n.value,e.get(n.field))&&(i=!0):n.type==="equalTo"?n.value===e.get(n.field)&&(i=!0):n.type==="containsAllOf"?t.isArray(n.value)&&t.isArray(e.get(n.field))&&t.intersection(n.value,e.get(n.field)).length===n.value.length&&(i=!0):n.type==="pattern"?e.get(n.field).toString().match(n.value)&&(i=!0):i=!1}),i&&r.push(e)}),r},_filter:function(n,r,i){var s=this,o={};t.isString(r)?o[r]={cmp_method:"regexp"}:t.isArray(r)?t.each(r,function(e){o[e]={cmp_method:"regexp"}}):t.each(r,function(e,n){o[n]=t.defaults(e,{cmp_method:"regexp"})}),r=o,t.has(e.Paginator,"removeDiacritics")&&s.useDiacriticsPlugin&&(i=e.Paginator.removeDiacritics(i));if(i===""||!t.isString(i))return n;var u=t.map(i.match(/\w+/ig),function(e){return e.toLowerCase()}),a="("+t.uniq(u).join("|")+")",f=new RegExp(a,"igm"),l=[];return t.each(n,function(n){var o=[];t.each(r,function(r,a){var l=n.get(a);if(l){var c=[];t.has(e.Paginator,"removeDiacritics")&&s.useDiacriticsPlugin?l=e.Paginator.removeDiacritics(l.toString()):l=l.toString();if(r.cmp_method==="levenshtein"&&t.has(e.Paginator,"levenshtein")&&s.useLevenshteinPlugin){var h=e.Paginator.levenshtein(l,i);t.defaults(r,{max_distance:0}),h<=r.max_distance&&(c=t.uniq(u))}else c=l.match(f);c=t.map(c,function(e){return e.toString().toLowerCase()}),t.each(c,function(e){o.push(e)})}}),o=t.uniq(t.without(o,"")),t.isEmpty(t.difference(u,o))&&l.push(n)}),l},info:function(){var e=this,t={},n=e.sortedAndFilteredModels?e.sortedAndFilteredModels.length:e.length,r=Math.ceil(n/e.perPage);return t={totalUnfilteredRecords:e.origModels.length,totalRecords:n,currentPage:e.currentPage,perPage:this.perPage,totalPages:r,lastPage:r,previous:!1,next:!1,startRecord:n===0?0:(e.currentPage-1)*this.perPage+1,endRecord:Math.min(n,e.currentPage*this.perPage)},e.currentPage>1&&(t.previous=e.currentPage-1),e.currentPage<t.totalPages&&(t.next=e.currentPage+1),t.pageSet=e.setPagination(t),e.information=t,t},setPagination:function(e){var t=[],n=0,r=0,i=this.pagesInRange*2,s=Math.ceil(e.totalRecords/e.perPage);if(s>1)if(s<=1+i)for(n=1,r=s;n<=r;n++)t.push(n);else if(e.currentPage<=this.pagesInRange+1)for(n=1,r=2+i;n<r;n++)t.push(n);else if(s-this.pagesInRange>e.currentPage&&e.currentPage>this.pagesInRange)for(n=e.currentPage-this.pagesInRange;n<=e.currentPage+this.pagesInRange;n++)t.push(n);else for(n=s-i;n<=s;n++)t.push(n);return t}}),r.requestPager=e.Collection.extend({sync:function(e,r,i){var s=this;t.defaults(s.paginator_ui,{firstPage:0,currentPage:1,perPage:5,totalPages:10,pagesInRange:4}),t.each(s.paginator_ui,function(e,n){t.isUndefined(s[n])&&(s[n]=s.paginator_ui[n])});var o={};t.each(t.result(s,"server_api"),function(e,n){t.isFunction(e)&&(e=t.bind(e,s),e=e()),o[n]=e});var u=t.clone(s.paginator_core);t.each(u,function(e,n){t.isFunction(e)&&(e=t.bind(e,s),e=e()),u[n]=e}),u=t.defaults(u,{timeout:25e3,cache:!1,type:"GET",dataType:"jsonp"}),i.data?i.data=decodeURIComponent(n.param(t.extend(o,i.data))):i.data=decodeURIComponent(n.param(o));var a=i.success;i.success=function(e,t,n){a&&a(e,t,n),r&&r.trigger&&r.trigger("sync",r,e,i)};var f=i.error;i.error=function(e,t,n){f&&f(r,e,i),r&&r.trigger&&r.trigger("error",r,e,i)},u=t.extend(u,{processData:!1,url:t.result(u,"url")},i);var l=n.ajax(u);return r&&r.trigger&&r.trigger("request",r,l,i),l},requestNextPage:function(e){if(this.currentPage!==undefined)return this.currentPage+=1,this.pager(e);var t=new n.Deferred;return t.reject(),t.promise()},requestPreviousPage:function(e){if(this.currentPage!==undefined)return this.currentPage-=1,this.pager(e);var t=new n.Deferred;return t.reject(),t.promise()},updateOrder:function(e){e!==undefined&&(this.sortField=e,this.pager())},goTo:function(e,t){if(e!==undefined)return this.currentPage=parseInt(e,10),this.pager(t);var r=new n.Deferred;return r.reject(),r.promise()},howManyPer:function(e){e!==undefined&&(this.currentPage=this.firstPage,this.perPage=e,this.pager())},sort:function(){},info:function(){var e={totalRecords:this.totalRecords||0,currentPage:this.currentPage,firstPage:this.firstPage,totalPages:Math.ceil(this.totalRecords/this.perPage),lastPage:this.totalPages,perPage:this.perPage,hasPrevious:!1,hasNext:!1};return this.currentPage>1&&(e.hasPrevious=this.currentPage-1),this.currentPage<e.totalPages&&(e.hasNext=this.currentPage+1),e.pageSet=this.setPagination(e),this.information=e,e},setPagination:function(e){var t=[],n=0,r=0,i=this.pagesInRange*2,s=Math.ceil(e.totalRecords/e.perPage);if(s>1)if(s<=1+i)for(n=1,r=s;n<=r;n++)t.push(n);else if(e.currentPage<=this.pagesInRange+1)for(n=1,r=2+i;n<r;n++)t.push(n);else if(s-this.pagesInRange>e.currentPage&&e.currentPage>this.pagesInRange)for(n=e.currentPage-this.pagesInRange;n<=e.currentPage+this.pagesInRange;n++)t.push(n);else for(n=s-i;n<=s;n++)t.push(n);return t},pager:function(e){return t.isObject(e)||(e={}),this.fetch(e)},url:function(){return this.paginator_core!==undefined&&this.paginator_core.url!==undefined?this.paginator_core.url:null}}),r}(Backbone,_,jQuery);