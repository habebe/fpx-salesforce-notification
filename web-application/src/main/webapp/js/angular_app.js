"use strict"

var module = angular.module("fpx-sfn-app",['ngCookies']).
controller("fpx-sfn-ctl-main",['$cookies','$http','$location',function ($cookies,$http,$location)
	{

	this.initializeCookies = function()
	{
		var savedNavigationState = $cookies.get('nav-state');
		if(typeof savedNavigationState == "string")
		{
			this.setNagivationState(savedNavigationState);
		}
		var savedOpportunityDataDescription = $cookies.get('op-state');
		if(typeof savedOpportunityDataDescription == "string")
		{
			this.opportunityDataDescription = JSON.parse(savedOpportunityDataDescription);
		}
	}

	this.setNagivationState = function(state)
	{
		this.navigationState = state;
		$cookies.put('nav-state',state);
	}
	this.getTableHeaderNames = function()
	{
		if(this.navigationState == this.opportunityNavKey)
			return this.opportunityDataDescription;
		else if(this.navigationState == this.criteriaNavKey)
			return this.criteriaDataDescription;
		else if(this.navigationState == this.notificationNavKey)
			return this.notificationDataDescription;
		return [];
	}

	this.getTableBriefData = function()
	{
		if(this.navigationState == this.opportunityNavKey)
			return this.opportunityData;
		else if(this.navigationState == this.criteriaNavKey)
			return this.criteriaData;
		else if(this.navigationState == this.notificationNavKey)
			return this.notificationData;
		return [];
	}
	this.navagitionItemClicked = function(state)
	{
		this.setDetailedView(false);
		this.setNagivationState(state);
	}

	this.isNavigationState = function(state)
	{
		return (this.navigationState  == state);
	}

	this.isDetailedView = function(state)
	{
		return this.detailedView;
	}

	this.refreshClicked = function()
	{
		if(this.navigationState == this.opportunityNavKey)
			this.refreshOpportunityData();
		else if(this.navigationState == this.criteriaNavKey)
			this.refreshCriteriaData();
		else if(this.navigationState == this.notificationNavKey)
			this.refreshNotificationData();
	}

	this.refreshUserInfo = function()
	{
		var url = "../user/get";
		var object = this;
		$http.get(url).
		success(function(data) { 
			object.userInfo = data;
		}
		).
		error(function (data, status, headers, config) 
				{
			object.userInfo = {};
				});
	}

	this.getUserName = function()
	{
		if(this.userInfo != null)
		{
			return this.userInfo["name"];
		}
		return null;
	}

	this.getUserId = function()
	{
		if(this.userInfo != null)
		{
			return this.userInfo["id"];
		}
		return null;
	}

	this.refreshData = function(object,url,dataType)
	{
		var result = null;
		$http.get(url).
		success(function(data) { 

			if(dataType == object.opportunityNavKey)
			{
				object.opportunityData = data;
				object.opportunityDataMap = {};
				for(var i=0;i<object.opportunityData.length;i++)
				{
					object.opportunityDataMap[object.opportunityData[i]["id"]] = i;
				}
			}
			else if(dataType == object.criteriaNavKey)
			{
				object.criteriaData = data["result"];
				object.criteriaDataMap = {};
				for(var i=0;i<object.criteriaData.length;i++)
				{
					object.criteriaDataMap[object.criteriaData[i]["id"]] = i;
				}
				console.log(JSON.stringify(object.criteriaDataMap));
			}
			else if(dataType = object.notificationNavKey)
			{
				object.notificationData = data["result"];
				object.numberOfNotifications = object.notificationData.length;
				object.hasNotifications = (object.numberOfNotifications > 0);
			}
		}
		).
		error(function (data, status, headers, config) {
			alert("SENDING TO /newValue.");
			$location.path('/newValue');
			
			//alert("ERROR Data " + data + " Status:" + status + " header:" + headers) ;
			result = data;
			object = [];
		});
	}

	function postCriterion(url,object,postData)
	{
		var result = null;
		$http.post(url,postData).
		success(function(data)
				{
			object.refreshCriteriaData();
				}).
				error(function (data, status, headers, config) {
					alert("SENDING TO /newValue.");
					$location.path('/newValue');
					//alert("ERROR Data " + data + " Status:" + status + " header:" + header) ;
					result = data;          
				});
	}

	this.deleteCriterion = function(requestData)
	{
		var url = "../criteria/delete";
		var object = this;
		$http.post(url,requestData).
		success(function(data)
				{
			object.refreshCriteriaData();
			object.refreshNotificationData();
				}).
				error(function (data, status, headers, config) {
					alert("SENDING TO /newValue.");
					$location.path('/newValue');
					//alert("ERROR Data " + data + " Status:" + status + " header:" + header) ;
					result = data;          
				});
	}

	this.refreshOpportunityData = function()
	{
		this.refreshData(this,"../opportunity/all",this.opportunityNavKey);
	}

	this.refreshCriteriaData = function()
	{
		this.refreshData(this,"../criteria/get",this.criteriaNavKey);
	}

	this.refreshNotificationData = function()
	{
		this.refreshData(this,"../notification/get",this.notificationNavKey);
	}

	this.shouldShowColumn = function(index)
	{
		var description = this.getTableHeaderNames();
		return description[index].show;
	}
	this.tablePropertyClicked = function(index)
	{
		if(this.navigationState == this.opportunityNavKey)
		{
			this.opportunityDataDescription[index].show = !(this.opportunityDataDescription[index].show);	
			$cookies.put('op-state',JSON.stringify(this.opportunityDataDescription));
		}
	}

	this.getDetailedViewData = function(index)
	{
		if(this.navigationState == this.opportunityNavKey)
		{
			return this.opportunityData[this.detailedViewIndex]; 
		}
		else if(this.navigationState == this.notificationNavKey)
		{
			return this.notificationData[this.detailedViewIndex]; 
		}
		return null;
	}

	this.setDetailedView = function(state)
	{
		this.detailedView = state;
	}

	this.setDetailedViewIndex = function(index)
	{
		this.detailedViewIndex = index;
	}

	this.getDetailedViewIndex = function()
	{
		return this.detailedViewIndex;
	}

	this.rowClicked = function(index)
	{
		this.currentNotificationIndex = null;
		if(this.navigationState == this.opportunityNavKey)
		{
			this.setDetailedView(true);
			this.setDetailedViewIndex(index);
		}
		else if(this.navigationState == this.notificationNavKey)
		{
			this.setDetailedView(true);
			this.setDetailedViewIndex(index);
			this.currentNotificationIndex = index;
		}
	}

	this.getCurrentCriteriaIndex = function()
	{
		return this.currentCriteriaIndex;
	}

	this.setCurrentCriteriaIndex = function(index)
	{
		this.currentCriteriaIndex = index;
	}

	this.getNumberOfItemInListView = function()
	{
		if(this.navigationState == this.opportunityNavKey)
			return this.opportunityData.length;
		else if(this.navigationState == this.criteriaNavKey)
			return this.criteriaData.length;
		else if(this.navigationState == this.notificationNavKey)
			return this.notificationData.length;
		return 0;
	}

	this.editCriterionClicked = function(isupdate,rowNumber)
	{
		this.refreshCriteriaData();
		if(isupdate)
		{
			this.criteriaEditMode = 2;
			this.criterionId = this.criteriaData[rowNumber].id;
			this.criterionDescription = this.criteriaData[rowNumber].name;
			this.criterionExpression = this.criteriaData[rowNumber].expression;
			this.criterionEnabled = this.criteriaData[rowNumber].enabled;
		}
		else
		{
			this.criteriaEditMode = 1;
			this.criterionId = null;
			this.criterionDescription = "";
			this.criterionExpression = "";
			this.criterionEnabled = true;
		}

	}

	this.saveCriteriaClicked = function()
	{
		var data = {
				"name":this.criterionDescription,
				"expression":this.criterionExpression,
				"enabled":this.criterionEnabled
		};
		var url; 		
		if(this.criteriaEditMode == 2)
		{
			data["id"] = this.criterionId;
			url = "../criteria/update";
		}
		else
		{
			url = "../criteria/add";
		}
		postCriterion(url,this,data);
		this.criteriaEditMode = 0;
		this.criterionDescription = "";
		this.criterionExpression = "";
		this.criterionEnabled = true;
	}


	this.deleteCriteriaClicked = function(rowNumber)
	{
		var data = {"id":this.criteriaData[rowNumber].id};
		this.deleteCriterion(data);
	}

	this.showDetailedOpportunity = function()
	{
		var notification = this.notificationData[this.currentNotificationIndex];
		var opportunityId = notification["opportunityId"];
		var opportunityIndex = this.opportunityDataMap[opportunityId];
		this.setNagivationState(this.opportunityNavKey);
		this.rowClicked(opportunityIndex);
	}

	this.navigationState 	= 0;
	this.criteriaEditMode 	= 0;
	this.notificationNavKey = 1;
	this.opportunityNavKey  = 2;
	this.criteriaNavKey     = 3;
	this.criterionDescription = "";
	this.criterionExpression = "";
	this.criterionEnabled = true;
	this.hasNotifications = false;

	this.opportunityDataDescription = [
		{"name":"id","show":false,"header":"Id"},
		{"name":"name","show":false,"header":"Name"},
		{"name":"accountId","show":false,"header":"AccountId"},
		{"name":"amount","show":false,"header":"Amount"},
		{"name":"closeDate","show":false,"header":"CloseDate"},
		{"name":"closed","show":false,"header":"IsClosed"},
		{"name":"createdById","show":false,"header":"CreatedById"},
		{"name":"createdDate","show":false,"header":"CreatedDate"},
		{"name":"currentGenerators__c","show":false,"header":"CurrentGenerators__c"},
		{"name":"deleted","show":false,"header":"IsDeleted"},
		{"name":"expectedRevenue","show":false,"header":"ExpectedRevenue"},
		{"name":"fiscal","show":false,"header":"Fiscal"},
		{"name":"fiscalQuarter","show":false,"header":"FiscalQuarter"},
		{"name":"fiscalYear","show":false,"header":"FiscalYear"},
		{"name":"forecastCategory","show":false,"header":"ForecastCategory"},
		{"name":"forecastCategoryName","show":false,"header":"ForecastCategoryName"},
		{"name":"hasOpenActivity","show":false,"header":"HasOpenActivity"},
		{"name":"hasOpportunityLineItem","show":false,"header":"HasOpportunityLineItem"},
		{"name":"hasOverdueTask","show":false,"header":"HasOverdueTask"},
		{"name":"lastModifiedById","show":false,"header":"LastModifiedById"},
		{"name":"lastModifiedDate","show":false,"header":"LastModifiedDate"},
		{"name":"mainCompetitors__c","show":false,"header":"MainCompetitors__c"},
		{"name":"ownerId","show":false,"header":"OwnerId"},
		{"name":"private","show":false,"header":"IsPrivate"},
		{"name":"probability","show":false,"header":"Probability"},
		{"name":"stageName","show":true,"header":"StageName"},
		{"name":"type","show":true,"header":"Type"},
		{"name":"won","show":true,"header":"Won"}
		];
	this.criteriaDataDescription = [
		{"name":"id","show":true,"header":"Id"},
		{"name":"owner","show":true,"header":"Owner"},
		{"name":"ownerId","show":true,"header":"OwnerId"},
		{"name":"name","show":true,"header":"Description"},
		{"name":"expression","show":true,"header":"Expression"},
		{"name":"enabled","show":true,"header":"Enabled"}
		];
	this.notificationDataDescription = [
		{"name":"id","show":false,"header":"Id"},
		{"name":"recipientId","show":false,"header":"Recipient Id"},
		{"name":"recipientName","show":false,"header":"Recipient"},
		{"name":"opportunityId","show":true,"header":"Opportunity Id"},
		{"name":"opportunityOwnerName","show":true,"header":"Opportunity Owner"},
		{"name":"opportunityOwnerId","show":false,"header":"Opportunity Owner Id"},
		{"name":"criteriaId","show":true,"header":"Criteria Id"},
		{"name":"criteriaOwnerId","show":false,"header":"Criteria Owner Id"},
		{"name":"criteriaOwnerName","show":true,"header":"Criteria Owner"},
		{"name":"message","show":true,"header":"Message"}	
		];

	this.detailedView = false;
	this.detailedViewIndex = 0;
	this.numberOfNotifications = 0;
	this.initializeCookies();
	this.opportunityData = [];
	this.opportunityDataMap = {};
	this.criteriaData = [];
	this.criteriaDataMap = {};
	this.currentNotificationIndex = null;
	this.currentCriteriaIndex = null;
	this.notificationData = [];
	this.userInfo = {"name":null,"id":null};
	this.refreshUserInfo();
	this.refreshOpportunityData();
	this.refreshCriteriaData();
	this.refreshNotificationData();
	}
]
);