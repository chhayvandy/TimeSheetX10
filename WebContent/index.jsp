<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
	<title>TIMESHEET X10</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script data-require="angularjs@1.3.6" data-semver="1.3.6" type="text/javascript"
	src="<c:url value="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"/>"></script>
	
	<script src="<c:url value="https://apis.google.com/js/client.js"/>"></script>
	<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
     <link rel="stylesheet" type="text/css" media="screen" 
    href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css"> 
    
   <!--  <link rel="stylesheet" type="text/css" media="screen" 
    href="/resources/assets/css/bootstrap-datetimepicker.min.css"> -->
    
    <script src="<c:url value="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js"/>"></script>
    <script src="<c:url value="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"/>"></script> 
	<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"/>"></script>
	
    
    <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js"/>"></script> 
	<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"/>"></script>
    
    <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.9/angular-cookies.js"/>"></script>
    
    <script src="<c:url value="/resources/assets/js/script.js"/>"></script>
    <%-- <script src="<c:url value="/resources/assets/js/lib/bootstrap-datetimepicker.min.js"/>"></script>  --%>
    <script src="<c:url value="/resources/assets/js/app.js"/>"></script>
    <spring:url value="/resources/images" var="images" />
   <%--  <img src="${images}/back.png"/> --%>
	<style type="text/css">
	.btn-primary{
    margin-right: 10px;
}
.container{
  margin: 20px 0;
}
	.weekend {
  /* color: red;*/
  background-color:#F78181;
}
#fixedbutton {
    position: fixed;
    bottom: 20px;
    left: 0px; 
}
	</style>
</head>

<body ng-app="App" ng-controller="myController" onload="checkCookie()">
<div id="span1"></div>
<div id="span2"></div>
<nav class="navbar navbar-default" style="height: 100px;background: #58ACFA;">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    
      <a class="navbar-brand" href="#">
      
        <img  src="resources/images/X10.png"  style=" width: 150px;height: 70px; margin-left: 100px;" >
      </a>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="" id="bs-example-navbar-collapse-1">
    
      <ul class="nav navbar-nav navbar-right">
		<li  ng-show="showdata" style="margin-right:100px;">
			<img  ng-src="{{g_image}}" style="border-radius: 50%; width: 70px;height: 70px;">
<!-- <span>{{gmail.id}}</span> -->
			<span style="font-size: 20px;">{{gmail.username}}</span>
			<p style="font-size: 20px;">{{gmail.email}}</p>
		</li>
        <input type="button" style="margin-right: 100px; margin-top: 30px;"   ng-click="onGoogleLogin()"  class="btn btn-warning" value="Login google account"
 ng-show="btnlogin">
        
      </ul>
    </div>
  </div>
</nav>

<div ng-show="errordata" align="center">

<h1 style="color: red;">{{error}}</h1>	

</div>
<!-- ----------------------------------------USERS------------------------------------------------------------ -->

<div align="center"  ng-show="showuser" style="margin-left: 50px;margin-right: 50px;">

<table class="table table-bordered table-striped" ng-show="showtable" >
  		<thead>
  		<tr>
  		<th style="text-align: center;text-size:30px; background: #0489B1;">TIMESHEET</th>
  		</tr>
  		<tr>
  		<th style="text-align: center;">NAME : {{gmail.username}}</th>
  		</tr>
  		<tr>
  		<th style="text-align: center;">
  	 <div >
  		<form >
      <div>
        <div id="date" class="input-append" datetimez ng-model="var1">
          <input is-open="status.opened" data-format="MM-yyyy" type="text" readonly style="height: 25px;" >
          
          
          </input>
          <span class="add-on" style="height: 25px; background: cyan;">
            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
          </span>
          
        </div>
      </div>
  		</form>
  		</div>
  		
  		</th>
  		</tr>
  		</thead>
  		
</table>
<form name="myform" novalidate>
<table id="tt" class="table table-bordered" ng-show="showtable2" >
			<thead style="background: #8181F7;">
  		<tr style="text-align: center;">
  		    <th style="text-align: center;" >OT</th>
  			<th style="text-align: center;" >Day</th>
  			<th style="text-align: center;">In</th>
  			<th style="text-align: center;">Out</th>
  			<th style="text-align: center;">Hours</th>
  			<th style="text-align: center;">Break</th>
  			<th style="text-align: center;">Project Name</th>
  			<th style="text-align: center;">Task/Job Discript</th>
  		</tr>
  		
  		</thead>
  		<tbody ng-repeat="list in lists"  >
  		<!-- *************************************************   Holiday   ***************************************************** -->
  			<tr ng-show="isWeekend($index+1)" class="weekend"   >
  			
  				<th style="text-align: center;" style="width: 30px;" >
					
				<input type="checkbox" ng-model="OT">
				</th>		
  				<th style="text-align: center;" style="width: 30px;" >{{$index+1}}</th>
  				<th style="text-align: center; width: 10%;" >
  				
  				 <input type="time" step="1800" ng-show="OT" ng-model="list.timeIn_h" style="width:100%;height: 25px;"  >
  				
  				</th>
  				<th style="text-align: center; width: 10%;">
  				
  				<input type="time" step="1800" ng-show="OT" ng-model="list.timeOut_h" style="width:100%;height: 25px;"  >
  				
  				</th>
  				<th style="text-align: center;width: 10%;">
  				
  				<input type="number"  ng-show="OT" ng-model="list.hours_h" style="width:100%;height: 25px;"  >
  				
  				</th>
  				<th style="text-align: center;width: 10%;">
  				
  				<input type="checkbox" ng-model="checkBreak" ng-show="OT">
  				<input style="width:70px;height: 25px;" type="number" ng-show="checkBreak" ng-model="list.breakTime_h" >
  				
  				</th>
  				<th style="text-align: center;">
  				
  				<input style="width:100%;height: 25px;" type="text" ng-show="OT"  ng-model="list.project_h" > 
  				
  				</th>
  				<th style="text-align: center;">
  				
  				<input style="width:100%;height: 25px;" type="text" ng-show="OT"  ng-model="list.descript_h" >
  				
  				</th>
  			</tr>
  			<!-- #########################################Holiday################################################# -->
  			
  			
  			<!-- ***************************************************Simple day************************************** -->
  			<tr ng-hide="isWeekend($index+1)"> 
  			   <th style="text-align: center;" style="width: 30 px ;"> </th>
  				<th style="text-align: center;" style="width: 30 px ;">{{$index+1}}</th>
  				<th style="text-align: center;">
  				
     				    <input type="time" step="1800" ng-model="list.timeIn" style="width:100%;height: 25px;"  >
     				   
  				</th>
  				<th style="text-align: center;">
     				  <input type="time" step="1800" ng-model="list.timeOut" style="width:100%;height: 25px;"  >
     				 
  				</th>
  				
  				<th style="text-align: center;">
  				
  				<input type="number"  ng-model="list.hours" style="width:100%;height: 25px;"  >
  				
  				</th>
  				<th style="text-align: center;">
  				
  				<input type="checkbox" ng-model="checkBreak" ng-click="SelectTimeBreak($event,$index)">
  				
  				<input style="width:70px;height: 25px;" type="number" ng-show="checkBreak" ng-model="list.breakTime" >
  				
  				</th>
  				<th style="text-align: center;">
  				
  				<input style="width:100%;height: 25px;" type="text"  ng-model="list.project" > 
  				<!-- {{timeIn}} --> 
  				</th>
  				<th style="text-align: center;">
  				<input style="width:100%;height: 25px;" type="text"  ng-model="list.descript" >
  				</th>
  		</tr>
  		<!-- ####################################################Simple Day########################################## -->
  		</tbody>
  			
</table>
<button style="margin-left: 20px;" class="btn btn-warning pull-right" type="submit" ng-click="SentData()" ng-show="showtable2"><span class="glyphicon glyphicon-send"></span> Sent TimeSheet</button>

<button class="btn btn-success pull-right" type="submit" ng-click="SaveData()" ng-show="showtable2" ><span class="glyphicon glyphicon-floppy-saved"></span>Save TimeSheet</button>



</form>
<!-- <td ng-show="isWeekend(date)" class="weekend">Weekend: {{date|date:'d MMMM yy'}}</td>
      			<td ng-hide="isWeekend(date)">Weekday: {{date|date:'d MMMM yy'}}</td>
</div> -->

<div style="height: 50px; width:100%;">

</div>


</div>





<!-- <div>******************************************ADMIN MIDDLE*******************************************************************-->




<div ng-show="showAdmin1"  class="container" style="margin-left: 100px;" >

	<div class="container" style="width: 90%;">

  <ul class="nav nav-tabs">
    <li class="active" ><a data-toggle="tab" href="#home" class="bg-info text-white">All TIMESHEETS sent </a></li>
    <li ><a data-toggle="tab" href="#menu1" class="bg-info text-white">Set Holiday</a></li>
    <li ><a data-toggle="tab" href="#menu2" class="bg-info text-white">User and Admin</a></li>
  </ul>
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <!-- <h1>TIMESHEET list</h1> -->
      <div class="container" style="margin-right: 300px; width:100%;" ng-show="TableUserSent">
      
      <div  style="margin-right: 20px;">
      		  <input class="pull-left" type="text"
           ng-model ="search.Username"
           style="width : 40%;height: 40px;font-size: 30px;"
           class="form-control" 
           placeholder="Search Username" >
      		  <h5 class="pull-right"> All users sent {{totalUsersent}}</h5>
      </div>
      	  <table class="table  table-bordered  ">
      	    <thead>
      	    	<tr style="text-align: center;">
      	    	  <th style="text-align: center;">             
      	    	  	Number
      	    	  </th>
      	    	  <th style="text-align: center;">
      	    	      User's sent date
      	    	   </th >
      	    	   <th style="text-align: center;">
      	    	      USERNAME
      	    	   </th >
      	    	   <th style="text-align: center;">
      	    	      EMAIL
      	    	   </th>
      	    	   <th style="text-align: center;">
      	    	      MONTH/YEAR
      	    	   </th >
      	    	   <th style="width: 10%;text-align: center;">
      	    	      Action
      	    	   </th >
      	    	</tr>
      	    </thead>
      	    <tbody >
      	       <tr style="text-align: center;" ng-repeat="Usersent in ListUsers | filter:search |limitTo: limit" >
      	        <td style="text-align: center;">
      	         	{{$index+1}}
      	         </td >
      	       	<td style="text-align: center;" ng-model="DateInsert">
      	         {{Usersent.TimeInsert +" / "}} {{Usersent.DateInsert}}
      	         </td >
      	         <td style="text-align: center;" ng-model="Username">
      	         {{Usersent.Username}}
      	         </td>
      	         <td style="text-align: center;" ng-model="Email">
      	         {{Usersent.Email}}
      	         </td>
      	         <td style="text-align: center;" ng-model="MonthYear">
      	         {{Usersent.MonthYear}}
      	         </td>
      	         <td  style="text-align: center;">
      	         <button class="btn btn-warning" ng-click="watch_table(Usersent)" >
      	         <span class="glyphicon glyphicon-eye-open"></span>
      	         </button>
      	         <!--< button class="btn btn-success">
      	         <span class="glyphicon glyphicon-ok"></span>
      	         </button>
      	         <button class="btn btn-danger">
      	         <span class="glyphicon glyphicon-remove"></span>
      	         </button> -->
      	         </td>
      	       </tr>
      	    </tbody>
      	 </table>
      	 
      	
      	 <div >
      	 	<button style="margin-left: 45%;" class="btn btn-default" ng-click="loadmore()"><span class="glyphicon glyphicon-repeat"></span style="font-size: 40px;">&nbsp;Load More</button>
      	 </div>
      	 
      
      </div>
      
     
      <div ng-show="showUserTableInAdmin">
      	<button ng-click="backTable()" class="btn btn-default" style="margin-bottom: 20px;"><span class="glyphicon glyphicon-chevron-left"></span>
      	</span>BACK</button>
  <div id="tableUsersent">
      	<table class="table table-bordered table-striped"  >
  		<thead>
  		<tr>
  		<th style="text-align: center;text-size:30px; background: #0489B1;">TIMESHEET</th>
  		</tr>
  		<tr>
  		<th style="text-align: center;">NAME : {{UsernameTable}}</th>
  		</tr>
  		<tr>
  		<th style="text-align: center;">Month-Year : {{MonthYear}} 
  		</th>
  		</tr>
  		</thead>
  		
</table>
<form>
<table id="tt" class="table table-bordered" >
			<thead style="background: #8181F7;">
  		<tr style="text-align: center;">
  		    
  			<th style="text-align: center;" >Day</th>
  			<th style="text-align: center;">In</th>
  			<th style="text-align: center;">Out</th>
  			<th style="text-align: center;">Hours</th>
  			<th style="text-align: center;">Break</th>
  			<th style="text-align: center;">Project Name</th>
  			<th style="text-align: center;">Task/Job Discript</th>
  		</tr>
  		
  		</thead>
  		<tbody ng-repeat="list in ListTableUsers"  >
  		<!-- *************************************************   Holiday   ***************************************************** -->
  		      <tr ng-show="isWeekend($index+1)" class="weekend">
  				<td style="text-align: center;" >{{$index+1}}</td>
  				<td style="text-align: center; width: 10%;" >
  				
  				 {{list.timeIn_h}}
  				
  				</td>
  				<td style="text-align: center; width: 10%;">
  				
  				{{list.timeOut_h}}
  				
  				</td>
  				<td style="text-align: center;width: 10%;">
  				
  				{{list.hours_h}}
  				
  				</td>
  				<td style="text-align: center;width: 10%;">
  				
  				
  				{{list.breakTime_h}}
  				
  				</td>
  				<td style="text-align: center;">
  				
  				 {{list.project_h}}
  				
  				</td>
  				<td style="text-align: center;">
  				
  				{{list.descript_h}}
  				
  				</td>
  			</tr>
  			<!-- #########################################Holiday################################################# -->
  			
  			
  			<!-- ***************************************************Simple day************************************** -->
  			 <tr ng-hide="isWeekend($index+1)"> 
  			   
  				<td style="text-align: center;" >{{$index+1}}</td>
  				<td style="text-align: center;">
  				
     				{{list.timeIn}}   
     				   
  				</td>
  				<td style="text-align: center;">
     				 
     				 {{list.timeOut}} 
  				</td>
  				
  				<td style="text-align: center;">
  				
  				{{list.hours}} 
  				</td>
  				<td style="text-align: center;">
  					{{list.breakTime}} 
  				</td>
  				<td style="text-align: center;">
  				{{list.project}} 
  			
  				</td>
  				<td style="text-align: center;">
  					{{list.descript}} 
  				</td>
  		</tr> 
  		<!-- ####################################################Simple Day########################################## -->
  		</tbody>
  			
</table>
</div>
<div>
<div id="editor"></div>
<button ng-click="backTable()" class="btn btn-default pull-left" style="margin-bottom: 20px;"><span class="glyphicon glyphicon-chevron-left "></span>
      	BACK</button>


<button  class="btn btn-danger pull-right"   ng-click="loadPDF(list)">  Load as PDF</button>
</div>
</form>
      </div>
    </div>
    
    <!--************************************Set Holiday************************  -->
    <div id="menu1" class="tab-pane fade">
      <h1>Set Holiday </h1>
			
			    <div class="container" style="width:100%;"  >
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form >
                        	 <div class="form-group">
                                <input type="submit" class="btn btn-primary addnew pull-left" value="Add New" ng-click="addNew()">
                            </div>
                        	<br>
                        	<br>
                        	<br>
                            <table class="table table-striped table-bordered">
                                <thead style="background-color: ">
                                    <tr>
                                        <th>Number</th>
                                        <th>Date</th>
                                        <th style="width:50%">Description</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                    <tr ng-repeat="personalDetail in personalDetails">
                                        <td>
                                            {{$index+1}}
                                        </td>
                                        
                                        <td>
                                        	<div id="date" class="input-append" date ng-model="personalDetail.date" >
         												 <input  is-open="status.opened" data-format="dd-MM-yyyy" type="text" readonly style="height: 25px;" ></input>
         												 <span class="add-on" style="height: 25px; background: pink;">
          												  <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
         												 </span>
      										</div>
  											
                                        <td>
                                            <input type="text" style="height: 30px; font-size: 18px; " class="form-control" ng-model="personalDetail.descript" required/></td>
                                    </tr>
                                </tbody>
                            </table>
						<div class="form-group">
                                
                                <input type="submit" class="btn btn-success  pull-right" value="Save Date" ng-click="savedate()">
                            </div>
                           
                        </form>
                    </div>		
    </div>
  </div>
</div>
</div>
</div>
        <!-- ############################################## Set Holiday######################################## -->

<!-- ********************************************************Manage admin and user************************************ -->
<div id="menu2" class="tab-pane fade">
      <h1>Manage User and Admin </h1>
	  <div ng-show="AdminUser">
	  <button class="btn btn-warning pull-right" style="margin-bottom: 20px;" ng-click="AddMember()">Add Members</button>
	  <br>
	  <table class="table table-bordered" ng-init="getMembers()" >
	  	<thead style="background-color: #0489B1">
	  		<tr>
	  			<th>Number</th>
	  			<th>USERNAME</th>
	  			<th>EMAIL</th>
	  			<th>POSITION</th>
	  			<th>ACTION</th>
	  		</tr>
	  	</thead>
	  	<tbody  ng-repeat="member in members">
	  		<tr>
	  			<td>{{$index+1}}</td>
	  			<td> <p ng-hide="isupdate(member.id)">  {{member.username}} </p>
	  			<input ng-show="isupdate(member.id)" class="form-control" ng-model="member.username">
	  			</td>
	  			<td> <p ng-hide="isupdate(member.id)">{{member.email}}</p>
	  			<input ng-show="isupdate(member.id)" class="form-control" ng-model="member.email">
	  			</td>
	  			<td> <p ng-hide="isupdate(member.id)">{{member.position}}</p>
	  			  <select  ng-show="isupdate(member.id)" ng-model="member.position" >
     					 <option ng-repeat="option in positions.name" value="{{option.position}}">{{option.position}}</option>
     			  </select>
	  			</td>
	  			<td>
	  				<button class="btn btn-info"    ng-show="edit(member.id)" ng-click="editMember(member.id)">Edit</button>
	  				<button class="btn btn-warning" ng-show="updateclick(member.id)" ng-click="updateMember(member)">Update</button>
	  				<button class="btn btn-default" ng-show="updateclick(member.id)" ng-click="cancel(member.id)">Cancel</button> 
	  				<button class="btn btn-danger"  ng-show="edit(member.id)" ng-click="deleteMember(member.id)" >Delete</button>
	  			</td>
	  		</tr>
	  	</tbody>
	  </table>
	  
	  </div>
	  



	  
	  <div ng-show="TB_AddMember">
	  <button ng-click="BackAdminUserTB()" class="btn btn-default pull-left" style="margin-bottom: 20px;"><span class="glyphicon glyphicon-chevron-left "></span>
      	BACK</button>
      	
      <select  ng-model="data1.model" ng-change="AddRows(data1.model)" style="width:10%;" " class=" pull-right">
      <option ng-repeat="option in data1.availableOptions" value="{{option.id}}">{{option.name}}</option>
      </select>	
      <form>
	  <table class="table table-bordered" >
	  	<thead style="background-color: #0489B1">
	  		<tr>
	  			<th style="width:30%;">USERNAME</th>
	  			<th style="width:40%;">EMAIL</th>
	  			<th style="width:20%;">POSITION</th>
	  			
	  		</tr>
	  	</thead>
	  	<tbody ng-repeat="row in listrows">
	  		<tr>
	  			<td>
	  				<input class="form-control" ng-model="row.username" placeholder="Username">
	  			</td>
	  			<td>
	  				<input  class="form-control" ng-model="row.email"  placeholder="Email">
	  			</td>
	  			<td>
	  				<select  ng-model="row.position"  placeholder="Position">
     					 <option ng-repeat="option in positions.name" value="{{option.position}}">{{option.position}}</option>
     				</select>
	  			</td>
	  			
	  		</tr>
	  	</tbody>
	  </table>
	  <button class="btn btn-success pull-right" ng-click="AddMembers()">Add Members</button>
	  </form>	
	 </div>
</div>

<!--######################################################## Manage Admin and user##################################  -->



</div>
</div>

</div>


<!--###############################################################AdminMiddle##########################################----------------------------------------------->
<button id="fixedbutton" style="margin-left: 5%;" onclick="logout()" class="btn btn-danger" ng-show="logout" >Logout</button>
<a href="" ></a>
<script >
		(function () {
			/* console.log("vandy"); */
			var p = document.createElement('script');
			p.type= 'text/javascript';

			p.async = true;
			p.src='https://apis.google.com/js/client.js?onload=onLoadFunction';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(p, s);
			// body...
		})();
</script>
<script>

</script>
</body>
</html>