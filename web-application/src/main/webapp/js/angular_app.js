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
		if(typeof savedOpportunityDataNames == "string")
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
		this.setNagivationState(state);
	}

	this.isNavigationState = function(state)
	{
		return (this.navigationState  == state);
	}

	this.refreshClicked = function()
	{
		if(this.navigationState == this.opportunityNavKey)
			this.refreshOpportunityData();
		else if(this.navigationState == this.criteriaNavKey)
			this.refreshCriteriaData();
	}

	this.refreshData = function(object,url,dataType)
	{
		var result = null;
		$http.get(url).
		success(function(data) { 

			if(dataType == object.opportunityNavKey)
			{
				object.opportunityData = data;
			}
			else if(dataType == object.criteriaNavKey)
			{
				object.criteriaData = data["result"];
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
			alert("ERROR Data " + data + " Status:" + status + " header:" + headers) ;
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
					alert("ERROR Data " + data + " Status:" + status + " header:" + header) ;
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
				}).
				error(function (data, status, headers, config) {
					alert("ERROR Data " + data + " Status:" + status + " header:" + header) ;
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
		if(this.navigationState == this.opportunityNavKey)
			return this.opportunityDataDescription[index].show;
		return true;
	}
	this.tablePropertyClicked = function(index)
	{
		if(this.navigationState == this.opportunityNavKey)
		{
			this.opportunityDataDescription[index].show = !(this.opportunityDataDescription[index].show);	
			$cookies.put('op-state',JSON.stringify(this.opportunityDataDescription));
		}
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


	this.navigationState 	= 0;
	this.criteriaEditMode 	= 0;
	this.notificationNavKey = 1;
	this.opportunityNavKey  = 2;
	this.criteriaNavKey     = 3;
	this.criterionDescription = "";
	this.criterionExpression = "";
	this.criterionEnabled = true;
	this.hasNotifications = true;



	this.opportunityDataDescription = [
		{"name":"accountId","show":false},
		{"name":"amount","show":false},
		{"name":"closeDate","show":false},
		{"name":"closed","show":false},
		{"name":"createdById","show":false},
		{"name":"createdDate","show":false},
		{"name":"currentGenerators__c","show":false},
		{"name":"deleted","show":false},
		{"name":"expectedRevenue","show":false},
		{"name":"fiscal","show":false},
		{"name":"fiscalQuarter","show":false},
		{"name":"fiscalYear","show":false},
		{"name":"forecastCategory","show":false},
		{"name":"forecastCategoryName","show":false},
		{"name":"hasOpenActivity","show":false},
		{"name":"hasOpportunityLineItem","show":false},
		{"name":"hasOverdueTask","show":false},
		{"name":"id","show":false},
		{"name":"lastModifiedById","show":false},
		{"name":"lastModifiedDate","show":false},
		{"name":"mainCompetitors__c","show":false},
		{"name":"name","show":false},
		{"name":"ownerId","show":false},
		{"name":"private","show":false},
		{"name":"probability","show":false},
		{"name":"stageName","show":true},
		{"name":"type","show":true},
		{"name":"won","show":true}
		];
	this.criteriaDataDescription = [
		{"name":"id","show":true},
		{"name":"owner","show":true},
		{"name":"ownerId","show":true},
		{"name":"name","show":true},
		{"name":"expression","show":true},
		{"name":"enabled","show":true}
		];
	this.notificationDataDescription = [
		{"name":"id","show":true},
		{"name":"userId","show":true},
		{"name":"opportunityOwnerId","show":true},
		{"name":"criteriaId","show":true},
		{"name":"timeEvaluated","show":true},
		{"name":"message","show":true},
		{"name":"userName","show":true},
		{"name":"opportunityOwnerName","show":true}
		];
	this.numberOfNotifications = 1;
	this.initializeCookies();
	this.opportunityData = [];
	this.criteriaData = [];
	this.notificationData = [
		{
			"id":"1",
			"userId":"fake",
			"opportunityOwnerId":"ofake",
			"criteriaId":"cfake",
			"timeEvaluated":"now",
			"message":"No real message",
			"userName":"John Smith",
			"opportunityOwnerName":"James Smith"
		}
		];
	this.refreshOpportunityData();
	this.refreshCriteriaData();
	this.refreshNotificationData();
	}]);
