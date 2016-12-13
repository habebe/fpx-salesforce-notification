"use strict"

var module = angular.module("son-app",['ngCookies']).
controller("son-main-ctl",['$cookies','$http','$location',function ($cookies,$http,$location)
	{
	this.criteriaEditMode = false;
	this.criteriaUpdateMode = false;
	this.criterionDescription = "";
	this.criterionExpression = "";
	this.criterionId = null;
	this.criterionEnabled = true;

	var savedPageState = $cookies.get('son-page-state');
	if(typeof savedPageState == "string")
	{
		this.pageState = savedPageState;
	}
	else
	{
		this.pageState = 1;
	}
	this.opporunityTabClicked = function()
	{
		this.pageState = 1;
		$cookies.put('son-page-state', 1);
	}
	this.criteriaTabClicked = function()
	{
		this.pageState = 2;
		$cookies.put('son-page-state', 2);
	}

	this.rowClicked = function(rowNumber)
	{
		alert("CLICKED" + rowNumber);
	}

	this.updateCriterionClicked = function(rowNumber)
	{
		this.criteriaUpdateMode = true;
		this.criterionId = this.criteriaListView[rowNumber].id
		this.criterionDescription = this.criteriaListView[rowNumber].name;
		this.criterionExpression = this.criteriaListView[rowNumber].expression;
		this.criterionEnabled = this.criteriaListView[rowNumber].enabled;
	}
	
	this.newCriterionClicked = function()
	{
		this.criteriaEditMode = true;
		this.criterionDescription = "";
		this.criterionExpression = "";
		this.criterionEnabled = true;
	}

	this.deleteCriteriaClicked = function(rowNumber)
	{
		var result = confirm("Are you sure you want to delete criteria:" + rowNumber);
		if(result == true)
		{
			var data = {"id":this.criteriaListView[rowNumber].id};
			deleteCriterion(this,data);
		}
	}

	this.saveCriteriaClicked = function(update)
	{
		this.criteriaEditMode = false;
		this.criteriaUpdateMode = false;
		var data = {
				"name":this.criterionDescription,
				"expression":this.criterionExpression,
				"enabled":this.criterionEnabled
		};
		var url = "../criteria/add";
		if(update == true)
		{
			data["id"] = this.criterionId;
			url = "../criteria/update";
			alert("UPDATE")
		}
		var result = postCriterion(url,this,data);
	}

	function postCriterion(url,object,postData)
	{
		var result = null;
		$http.post(url,postData).
		success(function(data)
				{
			result = data;
			getCriteria(object);
				}).
				error(function (data, status, headers, config) {
					alert("ERROR Data " + data + " Status:" + status + " header:" + header) ;
					result = data;		
				});
		return result;
	}

	function deleteCriterion(object,requestData)
	{
		var url = "../criteria/delete";
		var result = null;
		$http.post(url,requestData).
		success(function(data)
				{
			result = data;
			getCriteria(object);
				}).
				error(function (data, status, headers, config) {
					alert("ERROR Data " + data + " Status:" + status + " header:" + header) ;
					result = data;		
				});
		return result;
	}

	function getCriteria(object)
	{
		var url = "../criteria/get";
		var result = null;
		$http.get(url).
		success(function(data)
				{
			result = data["result"];
			object.criteriaListView = result;
				}).
				error(function (data, status, headers, config) {
					alert("ERROR Data " + data + " Status:" + status + " header:" + headers) ;
					result = data;
					object.criteriaListView = [];
				});
		return result;
	}

	this.cancelSaveCriteriaClicked = function()
	{
		this.criteriaEditMode = false;
		this.criteriaUpdateMode = false;
	}

	this.opporunityListView = [
		{"name":"Sale Services to Boeing","accountName":"Boeing","owner":"John Doe"},
		{"name":"Sale Services to IBM","accountName":"IBM","owner":"John Doe"},
		{"name":"Sale Services to Apple","accountName":"Apple","owner":"John Doe"}
		];

		getCriteria(this);
	}]);