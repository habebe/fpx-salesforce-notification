<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%

if (request.getSession().getAttribute("access") == null) {
%><jsp:forward
	page="../jsp/login.jsp" />
<%
	}
%>
<html lang="en" ng-app="fpx-sfn-app">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
	  content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Salesforce Notification</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	  rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/angular.js"></script>
    <script src="${pageContext.request.contextPath}/js/angular-cookies.js"></script>
    <script src="${pageContext.request.contextPath}/js/angular_app.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  </head>

  <body ng-controller="fpx-sfn-ctl-main as ctl">
    <nav class="navbar navbar-default navbar-static-top">
      <div class="container">
	<a class="navbar-brand" href="https://www.fpx.com"> 
	  <img alt="Brand" height="24px" width="70px" 
	  src="${pageContext.request.contextPath}/img/fpx_logo_black.png">
	</a>
	<ul class="nav navbar-nav">
          <li 
             ng-click="ctl.navagitionItemClicked(ctl.notificationNavKey)" 
             ng-class="{'active':ctl.isNavigationState(ctl.notificationNavKey)}">
            <a>Notifications <span class="label label-danger label-as-badge" 
            ng-show="(ctl.hasNotifications == true)"
            >{{ctl.numberOfNotifications}}</span></a>
          </li>
          <li ng-click="ctl.navagitionItemClicked(ctl.opportunityNavKey)"
              ng-class="{'active':ctl.isNavigationState(ctl.opportunityNavKey)}">
            <a href="">Opportunities</a>
          </li>
          <li 
             ng-click="ctl.navagitionItemClicked(ctl.criteriaNavKey)"
             ng-class="{'active':ctl.isNavigationState(ctl.criteriaNavKey)}">
            <a href="">Criteria</a>
          </li>
        </ul>

        <form action="${pageContext.request.contextPath}/authenticate/logout.action" method="post">
	  <button type="submit" class="btn btn-link navbar-btn navbar-right">Sign out</button>
        </form>		
      </div>
      
      
      
    </nav>

    <div class="container">
      <div class="panel panel-default">
	<div class="panel-heading">
  	  
  	  <button class="btn btn-link" ng-show="ctl.isNavigationState(ctl.criteriaNavKey)"
  	  ng-click="ctl.editCriterionClicked(false,0)" data-toggle="modal" data-target="#editCriteriaId"
  	  >
  	    <i class="glyphicon glyphicon-plus"></i>
  	  </button>
  	  
  	  <button class="btn btn-link" ng-click="ctl.refreshClicked()">
  	    <i class="glyphicon glyphicon-refresh"></i></button>
	  
  	  <button class="btn btn-link" data-toggle="modal" data-target="#tablePropertyId" 
  	  ng-show="ctl.isNavigationState(ctl.opportunityNavKey)">
  	    <i class="glyphicon glyphicon-wrench" ></i>
  	  </button>
	  
	  <!-- Modal -->
	  <div class="modal fade" id="tablePropertyId" role="dialog">
	    <div class="modal-dialog">
	      
	      <!-- Modal content-->
	      <div class="modal-content">
		<div class="modal-header">
		  <button type="button" class="close" data-dismiss="modal">&times;</button>
		  <h4 class="modal-title">Table property</h4>
		</div>
		<div class="modal-body">
		  <div class="form-group row" ng-repeat="i in ctl.opportunityDataDescription">
         	    <label class="col-xs-10 col-form-label">{{i.name}}</label>
		    <div class="col-xs-2" >
		      <input class="form-check-input" type="checkbox" 
			     ng-checked="i.show"
			     ng-click="ctl.tablePropertyClicked($index)"
			     />
		    </div>
		  </div>
	        </div>
		<div class="modal-footer">
		  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
	      </div>
	      
	    </div>
	  </div>
	</div>

	<div class="modal fade" id="editCriteriaId" role="dialog">
	    <div class="modal-dialog">
	      
	      <!-- Modal content-->
	      <div class="modal-content">
		<div class="modal-header">
		  <button type="button" class="close" data-dismiss="modal">&times;</button>
		  <h4 class="modal-title">Notification Criteria</h4>
		</div>
		<div class="modal-body">
		  <form>
		  			<div class="form-group row" ng-show="(ctl.criteriaEditMode == 2)">
						<label class="col-xs-2 col-form-label">Id</label>
						<div class="col-xs-10">
							<input class="form-control" type="text"
								ng-model="ctl.criterionId" placeholder="Id" disabled />
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-xs-2 col-form-label">Description</label>
						<div class="col-xs-10">
							<input class="form-control" type="text"
								ng-model="ctl.criterionDescription" id="example-text-input"
								placeholder="Description" required autofocus />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-2 col-form-label">Expression</label>
						<div class="col-xs-10">
							<input class="form-control" type="text"
								ng-model="ctl.criterionExpression" placeholder="Expression"
								required />
						</div>
					</div>

					<div class="form-group row">
						<label class="col-xs-2 col-form-label">Enable</label>
						<div class="col-xs-10">
							<input class="form-check-input" type="checkbox"
								ng-model="ctl.criterionEnabled" id="example-text-input" />
						</div>
					</div>
					
				</form>
	        </div>
		<div class="modal-footer">
		  <button class="btn btn-primary"
		  				data-dismiss="modal"
						ng-click="ctl.saveCriteriaClicked()">Save</button>
					<button class="btn btn-primary"
						data-dismiss="modal"
						ng-click="ctl.cancelSaveCriteriaClicked()">Cancel</button>
		</div>
	      </div>
	      
	    </div>
	  </div>

  	<table class="table">
 	  <thead class="thead-inverse">
	    <tr>
	      <th 
		 ng-repeat="i in ctl.getTableHeaderNames()" 
		 ng-show="ctl.shouldShowColumn($index)">
		{{i.name}}
	      </th>
	    </tr>
	  </thead>
	  
	  <tbody ng-repeat="i in ctl.getTableBriefData()">
	    <tr ng-click="ctl.rowClicked($index)"
		onMouseover="this.bgColor='#EEEEEE'"
		onMouseout="this.bgColor='#FFFFFF'">
	      <td 
	      ng-repeat="j in ctl.getTableHeaderNames()" 
	      ng-show="ctl.shouldShowColumn($index)">
	      {{i[j.name]}}
	      </td>
	      <td ng-show="ctl.isNavigationState(ctl.criteriaNavKey)">
		<button class="btn btn-link" ng-click="ctl.editCriterionClicked(true,$index)" data-toggle="modal" data-target="#editCriteriaId">
		  <i class="glyphicon glyphicon-edit"></i>
		</button>
  		<button class="btn btn-danger">
  		  <i class="glyphicon glyphicon-trash" ng-click="ctl.deleteCriteriaClicked($index)"></i>
  		</button>
	      </td>
	    </tr>
	  </tbody>
	</table>
      </div>
    </div>    
  </body>
</html>

