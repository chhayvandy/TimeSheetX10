

function checkCookie() 
{
    var user=getCookie("username");
    if (user != "") {
//    	console.log(user);
    	var jsonObj = $.parseJSON('[' + user + ']');
//    	console.log(jsonObj);
//        alert("Welcome again " + jsonObj[0]['name']);
        
    	 angular.element(document.getElementById('span2')).scope().loginBtn(); 
        angular.element(document.getElementById('span1')).scope().callUserInNg(jsonObj);
        
//        }
    } else 
    {
//    	console.log("empty");
      
    	
    }    
}

function getCookie(cname) 
{
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) 
    {
        var c = ca[i];
        while (c.charAt(0) == ' ') 
        {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) 
        {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function setCookie(cname,cvalue,exdays) 
{
//	alert("dsfasf");
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + JSON.stringify(cvalue) + ";" + expires + ";path=/";
    
}

function  deleteCookie()
{
	
    var user = getCookie("username");
        if (user != "") 
        {
        	
//        alert("Cookie was deleted");
        setCookie("username","", -1);
       
        }   
}
function  logout()
{
	deleteCookie();
	 location.reload();
       
}


var app = angular.module("App",['ngCookies']);
app.controller('myController',['$scope','$http','$cookieStore','$window', function($scope,$http,$cookieStore,$window){
//	$scope.numb = $scope.num_holiday;
	
	$scope.loginBtn = function()
	{
//		console.log("btn");
		$scope.btnlogin = false;
	}
	$scope.loadPDF = function()
	{
//		console.log($scope.ListTableUsers);
		
		var ListUserPDF = $scope.ListTableUsers;
		
		for(var i=0;i<ListUserPDF.length;i++)
			{
				ListUserPDF[i].username = $scope.UsernameTable;
				ListUserPDF[i].date     = $scope.MonthYear+"-"+1;
			}
		var method = "POST";
		var url    = "api/AdminMiddleController/loadAsPDF";
		var data   = ListUserPDF;
//		console.log("data:");
//		console.log(data);
		 $http({
	 			method:method,
	 			url:url,
	 			data:data
	 		}).then(function(res){
	 			
//	 			alert(res.data['fileName']);
	 			alert("You download PDF file successful in Download folder file name " +
	 					": /Users/X10/Downloads/Timesheet/ "+ res.data['fileName']);
	 			$scope.digitalSign(res.data['fileName']);
//	 			$scope.getPdf(res.data['fileName']);
				
			})
		
	}
	
//	$scope.getPdf = function(fileName){
//			var url  = "api/AdminMiddleController/digitalSignPDF";
//			var data = fileName;
//			var method = "POST";
//	
//		  $http({
//			  method : method,
//			  url : url,
//			  data :data,
//			  responseType: 'arraybuffer'})
//		  .success(function (data) {
//		    var file = new Blob([data], {type: 'application/pdf'});
//		    var fileURL = URL.createObjectURL(file);
//		    window.open(fileURL);
//		  });
//		}
	
	$scope.digitalSign = function(fileName){
		window.open("api/AdminMiddleController/digitalSignPDF"+fileName);
		//window.location.href = "api/AdminMiddleController/digitalSignPDF";
//		var method = "POST";
//		var url = "api/AdminMiddleController/digitalSignPDF";
//		var data = fileName;
//		console.log(data);
//		var fd = new FormData();
//		fd.append('data', data);
//
//		 $http({
//	 			method:method,
//	 			url:url,
//	 			data:data,
////	 			dataType: 'json',
//	 			headers: {
//				    'Content-Type': 'application/pdf'
//				  },
//	 			responseType: 'arraybuffer'
//			}).then(function(response) {
//				console.log("hello world");
////		    	  var file = new Blob([response.data], {
////		    		    type: 'application/pdf'
////		    		  });
////	              var fileURL = URL.createObjectURL(file);
////	              window.open(fileURL);
//	    		  
////	    		  $window.open(fileURL);
//				
//		    	  	  console.log(response.data);
////		    	  	  window.open(response.data);
//				
//		    	  	  var file = new Blob([response.data], {type: 'application/pdf'});
//		    		  var fileURL = URL.createObjectURL(file);
//
//		    		  var a = document.createElement('a');
//		    		  	a.href    = fileURL; 
//		    		    a.target      = '_blank';
//		    		    a.download    = "vandy" ;
//		    		    document.body.appendChild(a);
//		    		    a.click();
//				
////				  console.log(response.data);
////		            var blob = new Blob([response.data], {type: 'application/pdf'});
////		            saveAs(blob, 'vandy11.pdf');
//		    		    
//		    		    
////		    		    var anchor = angular.element('<a/>');
////		    	          var blob = new Blob([response.data]);
////		    	          anchor.attr({
////		    	            href: window.URL.createObjectURL(blob),
////		    	            target: '_blank',
////		    	            download: 'fileName.png'
////		    	          })[0].click();
//				
////		    	  	
//		    		}, function(response) {
//		    		  alert('call failed');
//		    		});
////		alert(fileName);
	}
	
	$scope.btnlogin=true;
	$scope.logout=false;
	GetUserSent();
	$scope.AdminUser = true;
	$scope.TB_AddMember= false;
	$scope.ListUsers = [];
	var limitStep = 5;
	$scope.limit = limitStep;
	$scope.dataShow = limitStep;
	
	$scope.AddMember = function()
	{
		$scope.AdminUser = false;
		$scope.TB_AddMember = true;
	}
	
	$scope.BackAdminUserTB = function()
	{
		$scope.AdminUser = true;
		$scope.TB_AddMember = false;
	}
	
	$scope.data1 = {
		    model: null,
		    availableOptions: [
		      {id: 1, name: '1'},
		      {id: 5, name: '5'},
		      {id: 10, name: '10'},
		      {id: 20, name: '20'}
		    ]
	};
	$scope.listrows = [{
			username: "",
			email :"",
			postion: ""
	}]
	$scope.AddRows = function(data)
	{
//		console.log(data);
		    $scope.listrows = [];
			
			for(var i=0;i<data;i++)
				{
				var rows  = {
						username : "",
						email : "",
						position:""
				}
					$scope.listrows.push(rows);
				}
	}
	$scope.positions ={
			name: 
				[
			      { position: 'Admin'},
			      { position: 'User'}
			     
			    ]
	};
	
	$scope.getMembers = function()
	{
//		console.log("hello");
		$http({
			method: "POST",
			url	  : 'api/AdminMiddleController/getMembers'
		}).then(function(res){
			$scope.members = [];
//			console.log("--resdata--")
//			console.log(res.data);
//			if(res.data>0)
//				{
//					alert("fjlksdajf");
//			console.log("--memberdata--")
					$scope.members = res.data;
//			console.log($scope.members)

//				}
		});
	}
	$scope.isupdate = function(index)
	{
		return false;
	}
	$scope.updateclick = function(id)
	{
		return false;
	}
	$scope.edit= function(id)
	{
		return true;
	};
	$scope.cancel = function(id)
	{
		$scope.edit= function(k)
		{
			return true;
		}
		$scope.getMembers();
		$scope.isupdate = function(index)
		{
			if(id==index){
			return false;
			}
		}
		$scope.updateclick = function(k)
		{
			if(k==id)
			return false;
		}
		
	}
	
	$scope.updateMember = function(member)
	{
		
		$scope.detailMember = {
				id : member.id,
				username : member.username,
				email : member.email,
				position : member.position
		};
		var data = $scope.detailMember;
//		console.log("data--------------");
//		console.log(data);
		$http({
			method: 'POST',
			url   : 'api/AdminMiddleController/updateMember',
			data : data
		}).then(function(res){
			if(res.data.success==true)
				{
				$scope.getMembers();
			alert('Update successfull!')
				}else{
					alert("fail1\!");
				}
		})
		
		$scope.updateclick = function(id)
		{
			if(id==member.id){
			   return false;
			}
			else {
				return false;
			}
		}
		$scope.edit = function(k)
		{
//			if(k==member.id)
//				{
				 return true;
//				}
//			else { return false;}
		};
		$scope.isupdate = function(index)
		{
			if(member.id==index){
			return false;
			}
		}
	}
	$scope.editMember = function(number)
	{
		
		$scope.updateclick = function(id)
		{
			if(id==number){
			   return true;
			}
			else {
				return false;
			}
		}
		$scope.edit = function(k)
		{
			if(k==number)
				{
				 return false;
				}
			else { return true;}
		};
		$scope.isupdate = function(index)
		{
			if(number==index){
			return true;
			}else{
				return false;
			}
		}
	}
	
	$scope.AddMembers = function()
	{
//		console.log($scope.listrows);
		var RowsUsers = [];
		for(var i=0;i<$scope.listrows.length;i++)
			{
				if($scope.listrows[i].username !='' && $scope.listrows[i].email !='' && $scope.listrows[i].position.length >0)
					{
						RowsUsers.push($scope.listrows[i]);
					}
			}
//		console.log(RowsUsers);
		
		if(RowsUsers.length>0)
			{
				var data = RowsUsers;
//				console.log(data);
				$http({
					method: 'POST',
					url   : 'api/AdminMiddleController/AddMembers',
					data : data
				}).then(function(res){
					if(res.data.success==true)
						{
							$scope.getMembers();
							alert("insert Members successfull! ");
							
						}
					else{
						alert("fail!!");
					}
				})
			}
		else{
			alert("You have to insert data into row!")
		}
		
		
	}
	
	
	$scope.deleteMember = function(id)
	{
		 $scope.MemberID = {
				 id : id
		 }
		 var data = $scope.MemberID;
//		 console.log(data);
		 $http({
			 method : 'POST',
			 url : 'api/AdminMiddleController/deleteMember',
			 data : data
		 }).then(function(res){
			 
			 if(res.data.success==true)
				 {
				 $scope.getMembers();
				  alert("delete successful!");
				 }
			 else{
				 alert("fail!");
			 }
			 
		 })
	}
	
	$scope.loadmore = function()
	{
		$scope.limit = $scope.limit+limitStep;
		if($scope.limit<$scope.ListUsers.length)
		{
			$scope.dataShow = $scope.limit;
		}else if($scope.ListUsers.length<$scope.limit)
			{
				$scope.dataShow = $scope.ListUsers.length;
			}
		else if($scope.limit>$scope.ListUsers.length){
				$scope.dataShow = $scope.ListUsers.length;
		}
		
		
	}
	$scope.TableUserSent 	= true;
	$scope.showUserTableInAdmin = false;
	
	$scope.backTable = function()
	{
		$scope.TableUserSent 	= true;
		$scope.showUserTableInAdmin = false;
	}
	
//	window.setInterval(function(){
//		$scope.ListUsers = [];
//		GetUserSent();
//     
//	}, 5000); 
//	
	function GetUserSent()
	{
		    
			var method = "POST";
			var url = "api/AdminMiddleController/loadUserSent";
			$http({
				method : method,
				url : url
			}).then(function(res){
			      
				for(var i =0 ;i<res.data.length;i++){
					var listUser = {
							
							Username : res.data[i]['Username'],
							Email    : res.data[i]['Email'],
							TimeInsert:res.data[i]['TimeInsert'],
							DateInsert:res.data[i]['DateInsert'],
							MonthYear :res.data[i]['MonthYear']
					
					}
					$scope.ListUsers.push(listUser);
				}
				$scope.totalUsersent = $scope.ListUsers.length;
				
				
			})
		
		
	}
	var sentData = false;
	$scope.SentData = function()
	{
		sentData= true;
		$scope.SaveData();
		
	}
	
	
	$scope.SaveData = function()
	{
		
		var listDataTimeSheet = [];
		var listDataTimeSheet_holiday = [];
		var listDataAdded = [];
		var listNewDataAdd = [];
		var ListDataSent   = [];
		var haveDataAddedorNot = false;
		var num = 0; 
		var num1 = 0;
//		console.log("listDay :"+$scope.listDay.length)
		if($scope.listDay.length>0)
			{
				for(var i = 0;i<$scope.lists.length;i++)
					{
					 if($scope.lists[i].hours > 0 && $scope.lists[i].timeIn != null && $scope.lists[i].timeOut != null )
						 {
						 	var day = i+1;
						 	
						 	for(var i1=0;i1<$scope.listDay.length;i1++)
						 		 {
						 			
						 		 
						 		    if(i+1==$scope.listDay[i1])
						 		    	{
						 		    	var HolidayOrNot = false;
						 		    	
						 		    	 for(var i2=0;i2<$scope.listHoliday.length;i2++)
						 		    		 {
						 		    		 	if(i+1==$scope.listHoliday[i2])
						 		    		 		{
						 		    		 		$scope.lists[i].timeIn = $scope.lists[i].timeIn_h;
						 		    		 		$scope.lists[i].timeOut = $scope.lists[i].timeOut_h;
						 		    		 		$scope.lists[i].hours = $scope.lists[i].hours_h;
						 		    		 		$scope.lists[i].BreakTime = $scope.lists[i].BreakTime_h;
						 		    		 		$scope.lists[i].project = $scope.lists[i].project_h;
						 		    		 		$scope.lists[i].descript = $scope.lists[i].descript_h;
						 		    		 		listDataAdded.push($scope.lists[i]);
						 		    		 		listDataAdded[num].date = $scope.monthyear+"-"+day;
								 		    		listDataAdded[num].activity = "Update";
								 		    		listDataAdded[num].features = "Holiday";
								 		    		listDataAdded[num].email = $scope.gmail.email;
								 		    		listDataAdded[num].username = $scope.gmail.username;
								 		    		num = num +1;
						 		    		 		HolidayOrNot = true;
						 		    		 		 break;
						 		    		 		}
						 		    		 }
						 		    	 if(HolidayOrNot==false)
						 		    		 {
						 		    		 	listDataAdded.push($scope.lists[i]);
						 		    		 	listDataAdded[num].date = $scope.monthyear+"-"+day;
							 		    		listDataAdded[num].activity = "Update";
							 		    		listDataAdded[num].features = "SimpleDay";
							 		    		listDataAdded[num].email = $scope.gmail.email;
							 		    		listDataAdded[num].username = $scope.gmail.username;
							 		    		num = num +1;
							 		    		haveDataAddedorNot = true;
						 		    		 }
						 		    	break;
						 		    		
						 		    	}
						 		 }
						 	
						 	var NumAddNew = 0;
						 	for(var i1=0;i1<$scope.listDay.length;i1++)
					 		 {
					 		    if(i+1!=$scope.listDay[i1])
					 		    	{
					 		    		NumAddNew = NumAddNew+1;
					 		    	}
					 		 }
						 	 if(NumAddNew==$scope.listDay.length)
						 		 {
//						 		 console.log("day1: "+day);
						 		listNewDataAdd.push($scope.lists[i]);
						 		listNewDataAdd[num1].date = $scope.monthyear+"-"+day;
						 		listNewDataAdd[num1].username 	= $scope.gmail.username;
						 		listNewDataAdd[num1].email 		= $scope.gmail.email;
						 		listNewDataAdd[num1].features 	= "Simpleday";
						 		listNewDataAdd[num1].activity 	= "New";
						 		num1= num1 + 1;
						 		 }
						 }
//					 console.log("vandy");
//					 console.log(listDataAdded);
					 if($scope.lists[i].hours_h > 0 && $scope.lists[i].timeIn_h != null && $scope.lists[i].timeOut_h != null )
			      	  {
						    var day = i+1;
						    var NumAddNew = 0;
						 	for(var i1=0;i1<$scope.listDay.length;i1++)
					 		 {
					 		    if(i+1!=$scope.listDay[i1])
					 		    	{
					 		    		NumAddNew = NumAddNew+1;
					 		    	}
					 		 }
						 	 if(NumAddNew==$scope.listDay.length)
						 		 {
//						 		 console.log("day1: "+day);
						 		listNewDataAdd.push($scope.lists[i]);
						 		listNewDataAdd[num1].timeIn    = listNewDataAdd[num1].timeIn_h;
								listNewDataAdd[num1].timeOut   = listNewDataAdd[num1].timeOut_h;
								listNewDataAdd[num1].hours     = listNewDataAdd[num1].hours_h;
								listNewDataAdd[num1].breakTime = listNewDataAdd[num1].breakTime_h;
								listNewDataAdd[num1].project   = listNewDataAdd[num1].project_h;
								listNewDataAdd[num1].descript  = listNewDataAdd[num1].descript_h;
								
						 		listNewDataAdd[num1].date	    = $scope.monthyear+"-"+day;
						 		listNewDataAdd[num1].username 	= $scope.gmail.username;
						 		listNewDataAdd[num1].email 		= $scope.gmail.email;
						 		listNewDataAdd[num1].features 	= "Holiday";
						 		listNewDataAdd[num1].activity 	= "New";
						 		num1= num1+1;
						 		 }
			      	  }
					 	
					}

//				console.log(listDataAdded);
//				console.log(listNewDataAdd);
			}
		else 
			{
				var num1 = 0;
				
				for(var i=0;i<$scope.lists.length;i++)
					{
						if($scope.lists[i].hours > 0 && $scope.lists[i].timeIn != null && $scope.lists[i].timeOut != null)
							{
							var day = i+1;
							listNewDataAdd.push($scope.lists[i]);
							listNewDataAdd[num1].date = $scope.monthyear+"-"+day;
					 		listNewDataAdd[num1].username 	= $scope.gmail.username;
					 		listNewDataAdd[num1].email 		= $scope.gmail.email;
					 		listNewDataAdd[num1].features 	= "Simpleday";
					 		listNewDataAdd[num1].activity 	= "New";
					 		num1= num1 + 1;
							}
						if($scope.lists[i].hours_h > 0 && $scope.lists[i].timeIn_h != null && $scope.lists[i].timeOut_h != null )
				      	  {
							var day = i+1;
							listNewDataAdd.push($scope.lists[i]);
							listNewDataAdd[num1].timeIn    = listNewDataAdd[num1].timeIn_h;
							listNewDataAdd[num1].timeOut   = listNewDataAdd[num1].timeOut_h;
							listNewDataAdd[num1].hours     = listNewDataAdd[num1].hours_h;
							listNewDataAdd[num1].breakTime = listNewDataAdd[num1].breakTime_h;
							listNewDataAdd[num1].project   = listNewDataAdd[num1].project_h;
							listNewDataAdd[num1].descript  = listNewDataAdd[num1].descript_h;
							
							listNewDataAdd[num1].date = $scope.monthyear+"-"+day;
					 		listNewDataAdd[num1].username 	= $scope.gmail.username;
					 		listNewDataAdd[num1].email 		= $scope.gmail.email;
					 		listNewDataAdd[num1].features 	= "Holiday";
					 		listNewDataAdd[num1].activity 	= "New";
							num1 = num1+1;
							
				      	  }
					}
//				console.log(listNewDataAdd);
			}
//		console.log("new Add :"+listNewDataAdd.length);
		var InsertData = false;
	if(listNewDataAdd.length>0){
		
		 var method = "POST";
         var data = listNewDataAdd;
         InsertData = true;
//         if(sentData==false)
//        	 {
//         listNewDataAdd = [] ;
//         	data = listNewDataAdd;
//        	 }
         var url = "api/UserController/insertTimeSheet";        //---------------------Insert TimeSheet Data------------
//		 console.log(data);
         $http({
 			method:method,
 			url:url,
 			data:data
 		}).then(function(res){
		  if(res.data.success){
			
			  alert("You insert Data TimeSheet successfull! ");
			}
		  else{
			  alert("You insert Data Failed!!");
		  }
			
		})
		}
		
		var UpdateData = false;
		if(haveDataAddedorNot==true )
			{
//			console.log("haveDataAddedorNot :"+haveDataAddedorNot)
			 var method = "POST";
	         var data = listDataAdded;
	         UpdateData = true;
	         var url = "api/UserController/updateTimeSheet";        //---------------------Update TimeSheet Data------------
//			 console.log(data);
	         $http({
	 			method:method,
	 			url:url,
	 			data:data
	 		}).then(function(res){
			  if(res.data.success)
			  	{
				  $scope.UpdateData = true;
				  alert("You Update Data TimeSheet successfull! ");
				}
			  else
			  {
				  alert("You Update Data Failed!!");
			  }
				
			})
		}
		
//		console.log("updatadata :"+ UpdateData);
//		console.log("insertdata :"+ InsertData);
		
		
		if((listDataAdded.length>0 || listNewDataAdd.length>0) && sentData==true)
			{   
				if(listDataAdded.length>0){
					for(var i=0;i<listDataAdded.length;i++){
							ListDataSent.push(listDataAdded[i]);
					}
				}
				$scope.listdayhave = [];
				var listdayhave = [];
				if(listNewDataAdd.length>0){
					for(var i=0;i<listNewDataAdd.length;i++){
						ListDataSent.push(listNewDataAdd[i]);
				}
				}
//				console.log("hahah: ");
//				console.log(ListDataSent);
				
				var method = "POST";
				var url = "api/UserController/updateFinalSentTimeSheet";
				var data = ListDataSent;
				$http({
					
					method : method,
					url : url,
					data : data
					
				}).then(function(res){
//					console.log(res.data);
					for(var n=0;n<res.data.length;n++)
						{
						$scope.listdayhave.push(res.data[n]['day']);
						}
					
				})
					var method = "POST";
					var url = "api/UserController/insertFinalSentTimeSheet";
					var data = ListDataSent;
					 listNewDataAdd = [] ;
					$http({
						
						method : method,
						url : url,
						data : data
						
					}).then(function(res){
						
						
						
					})
				
			}
		else if(listDataAdded.length==0 && listNewDataAdd.length==0)
			{
			    sentData=false;
				alert("You cannot sent or save TIMESHEET !!!");
			
			}
		
		if((InsertData==true || UpdateData==true ) && sentData==true)
			{
			 
//			  console.log("Email :"+ $scope.gmail.email);
//			  console.log("Name :"+ $scope.gmail.username);
//			  console.log("MonthYear :"+$scope.monthyear+"-"+1);
			  var offset = new Date();
			  var h = offset.getHours();
			  var m = offset.getMinutes();
			  
			  var timeInsert = h+":"+m;
			  var day = offset.getDate();
			  var month = offset.getMonth()+1;
			  var year = offset.getFullYear();
			  
			  var dateInsert = year+"-"+month+"-"+day

//			  console.log("date :"+dateInsert);
			  
			  $scope.UserAddorUpdate = 
				  {
					  email : $scope.gmail.email,
					  username : $scope.gmail.username,
					  monthYear : $scope.monthyear,
					  dateInsert: dateInsert,
					  timeInsert: timeInsert
				  }
//			  console.log($scope.UserAddorUpdate);
			  var method = "POST";
			  var data = $scope.UserAddorUpdate;
			  var url = "api/UserController/insertUserUpdate";
//			  console.log(data);
			  
			  $http({
				  method : method,
				  url : url ,
				  data :data
					  
			  }).then(function(res){
				 
				  
			  })
			  sentData=false;
			}
		
	}
	//****************************************************************************************************************************
	
$scope.watch_table = function(Usersent)
	{
		
		$scope.MonthYear = Usersent.MonthYear;
		$scope.UsernameTable = Usersent.Username;
		$scope.showUserTableInAdmin = true;
		$scope.TableUserSent 	= false;
		
	
		$scope.Usersent  =
			{
				date : Usersent.MonthYear+"-"+01,
				username : Usersent.Username
			}
    var holiday = Usersent.MonthYear+"-"+01  ;
//    console.log("holiday :"+ holiday );

    $scope.DateHoliday = {
    		date : holiday
    }
    
    
	var Month = new Date(Usersent.MonthYear).getMonth()+1;
    var Year = new Date(Usersent.MonthYear).getFullYear();
//    console.log("Month :"+ Month);
//    console.log("Year :"+ Year);
    var days  = new Date(Year,Month,0).getDate();
//    console.log("Days : "+ days);
    
    
    var  method = "POST";
    var url ="api/UserController/loadHoliday";   ///-----------------------Load Holiday---------------
    var data = $scope.DateHoliday ;
//    console.log(data);
    $http({
		method:method, 
		url:url,
		data:data
	}).then(function(res){
//		console.log(res.data);
		var listDay = res.data;
		var listSimpleDay = [];
		$scope.isWeekend = function (date) {
//			console.log(listDay);
			
			for(var kk=0;kk<listDay.length;kk++)
				{
				   if(date==listDay[kk]["dayIn"])
					   {
//					     console.log(" holiday :"+date);
					      return true;
					   }
				}
        	var date1 = new Date(Year,Month-1,date);
        	if(date1.getDay() === 6|| date1.getDay() === 0  ){
//        		console.log("day sun sat :"+date);
        		return true;
        	}
        	else{
        		return false;
        	}
        	
        };
	})
//	

////***************************************************Get Data Sent *************************************************    		
	var method = "POST";
    var url = "api/AdminMiddleController/loadAdminTableUserSent";      //------------------Load Data Sent-------------------
    $scope.GetDataSent = 
    {
    		email : Usersent.Email,
    		date : holiday
    }
    var data = $scope.GetDataSent;
//    console.log("data: ")
//    console.log(data);
    $http({
		method:method, 
		url:url,
		data:data
	}).then(function(res){
		
//		console.log(res.data);
		
		 $scope.listDay = [];
		 $scope.ListTableUsers = []
         $scope.listHolidayAdmin = [];
		
		for(var k=0;k<res.data.length;k++)
		{
			$scope.listDay.push(res.data[k]['day']);
		}
		for(var k=0;k<res.data.length;k++)
		{
//			scope.listDay.push(res.data[k]['day']);
			if(res.data[k]['Features']=="Holiday")
				{
					$scope.listHolidayAdmin.push(res.data[k]['day']);
//					console.log(res.data[k]['day']);
				}
		}
//		console.log("holiday :");
//		console.log($scope.listHolidayAdmin);
////		console.log(listDay);
////		console.log("day :"+day);
		
		
		
		var j1 = 0;
		for(var j=0;j<days;j++)
		{
			var SimpleOrHoliday = false;
			
			for(var jj=0;jj<$scope.listDay.length;jj++)
				{
				    if(j+1==$scope.listDay[jj])
				    	{
				    	j1=j1+1;
				    	SimpleOrHoliday= true;
				    	break;
				    	}
				}
			if(SimpleOrHoliday==true)
				{
				 var list1 =  {
		            		timeIn      : res.data[j1-1]["TimeIn"],
							timeOut		: res.data[j1-1]["TimeOut"],
							hours 		: res.data[j1-1]["hours"],
							breakTime	: res.data[j1-1]["BreakTime"],
							project		: res.data[j1-1]["project"],
							descript	: res.data[j1-1]["descript"],
							timeIn_h 	: res.data[j1-1]["TimeIn"],
							timeOut_h	: res.data[j1-1]["TimeOut"],
							hours_h 	: res.data[j1-1]["hours"],
							breakTime_h	: res.data[j1-1]["BreakTime"],
							project_h	: res.data[j1-1]["project"],
							descript_h	: res.data[j1-1]["descript"]
		            	}
            	$scope.ListTableUsers[j] = list1;
				}
			else{
				 var list1 =  {
		            		timeIn : "",
							timeOut:"",
							hours : "",
							breakTime: "",
							project :"",
							descript:"",
							
								timeIn_h : "",
								timeOut_h:"",
								hours_h : "",
								breakTime_h: "",
								project_h :"",
								descript_h:""
		            	}
            	$scope.ListTableUsers[j]=list1;
			}
		}
		
	});
	}
	//////////////////////////////////////////             Google Login                        /////////////////////////////////////
	$scope.gmail = {
		username  : "",
		id 		  : "",
		email :     ""
	};
	
	$scope.onGoogleLogin = function(){
		
		var  params = {

			'clientid' :     '455985280189-fs3g1m6eg89pq2dddq9efqbhe7arpcm0.apps.googleusercontent.com',
			'cookiepolicy' : 'single_host_origin',
			'callback' : function(result){
				if (result['status']['signed_in']) {
					var request = gapi.client.plus.people.get(
					{
						'userId' :'me'
					}
					);
					request.execute(function(resp){
						
						var email1 = resp.emails[0].value;
     					var parts = email1.split("@");
     					var notMember = false;
     					$scope.emaildata = {
     							email : email1
     					}
     					var data = $scope.emaildata;
     					$http({
     						method: 'POST',
     						url   : 'api/MemberController/checkEmail',
     						data  : data 
     					}).then(function(res){
//     						console.log("-------------fdd");
//     						console.log(res.data);
     						
     						if(res.data['position']=="Admin"
     							|| email1=='vandy.chhay26@gmail.com'
     								)
     							{
//     							console.log("position :"+ res.data.position);
     							$scope.errordata=false;
    							$scope.btnlogin=false;
    							$scope.showdata=true;
    							$scope.showuser = false;
    							$scope.showAdmin1=true;
    							$scope.logout=true;
     							}
     						else if(res.data['position']=="User")
     							{
//     							console.log("position :"+ res.data.position);
     							$scope.showuser = true;
    							$scope.showtable=true;
    							$scope.showdata=true;
    							$scope.errordata=false;
    							$scope.btnlogin=false;
    							$scope.showAdmin1=false;
    							$scope.logout=true;
     							}
     						else if( res.data['position']=="No")
     							{
     							$scope.showAdmin1=false;
    							$scope.showtable=false;
    							$scope.showtable2=false;
    							$scope.btnlogin=true;
    							$scope.showdata=false;
    							$scope.errordata=true;
    							$scope.logout=false;
    							deleteCookie();
//    							console.log("not member!")
//    							$scope.$apply(function()
//    									{
    						    notMember = true;
    							$scope.error      = " You are not our member ! ";	
    							
//    									})
     							}
     					})
     					
						$scope.$apply(function(){
							$scope.gmail.id       = resp.id;
							$scope.gmail.username = resp.displayName;
							$scope.gmail.email    = resp.emails[0].value;
							$scope.g_image        = resp.image.url;
							var user = {id:$scope.gmail.id,name:$scope.gmail.username,email:$scope.gmail.email,img:$scope.g_image}
							if(notMember==false){
							setCookie("username", user, 30);
							}

						});
					});

				}
				

			},
			'approvalprompt' : 'force',
//			 'scope'			 : 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
			'scope' : 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email'
		};
		  gapi.auth.signIn(params);
		  $scope.btnlogin = false;
	}
	
	$scope.callUserInNg = function(jsonObj){
		
		var email1 = jsonObj[0].email;
			
			var parts = email1.split("@");
			$scope.emaildata = {
						email : email1
				}
				var data = $scope.emaildata;
//			console.log(data);
			$http({
					method: 'POST',
					url   : 'api/MemberController/checkEmail',
					data  : data 
				}).then(function(res){
//					console.log("-------------fdd");
//					console.log(res.data);
					
					if(res.data['position']=="Admin"
						|| email1=='vandy.chhay26@gmail.com'
							)
						{
//					console.log("position :"+ res.data.position);
					$scope.errordata=false;
					$scope.btnlogin=false;
					$scope.showdata=true;
					$scope.showuser = false;
					$scope.showAdmin1=true;
					$scope.logout=true;
						}
					else if(res.data['position']=="User")
						{
//					console.log("position :"+ res.data.position);
					$scope.showuser = true;
					$scope.showtable=true;
					$scope.showdata=true;
					$scope.errordata=false;
					$scope.btnlogin=false;
					$scope.showAdmin1=false;
					$scope.logout=true;
						}
					else if( res.data['position']=="No")
						{
						$scope.showAdmin1=false;
					$scope.showtable=false;
					$scope.showtable2=false;
					$scope.btnlogin=true;
					$scope.showdata=false;
					$scope.errordata=true;
					$scope.logout=false;
					
					deleteCookie();
//					console.log("not member!")

				    notMember = true;
					$scope.error      = " You are not our member ! ";	
					
						}
				})
			

		$scope.$apply(function(){
			$scope.gmail.id       = jsonObj[0].id;
			$scope.gmail.username = jsonObj[0].name;
			$scope.gmail.email    = jsonObj[0].email;
			$scope.g_image        = jsonObj[0].img;
//			var user = {id:$scope.gmail.id,name:$scope.gmail.username,email:$scope.gmail.email,img:$scope.g_image}
//			setCookie("username", user, 30);

		});
		
		
		
	}
	
	//**************************************************AdminMiddle************************************************
	$scope.personalDetails = [{}];
        $scope.addNew = function(personalDetail){
        	$scope.personalDetails.push({ 
            	});
        };
        $scope.savedate = function()
        {
          var listdate = [];
          for(var i =0;i<$scope.personalDetails.length;i++)
          {
        	  if($scope.personalDetails[i].date!= null)
        	  {
        		 listdate.push($scope.personalDetails[i]);
        	     console.log($scope.personalDetails[i].date);
        	  }
          }
          var method = "POST";
          var data = listdate;
          listdate = [];
          var url = "api/AdminMiddleController/insertDate";//----------------Insert Holiday --------------------------
          console.log(data);
          $http({
  			method:method,
  			url:url,
  			data:data
  		}).then(function(res){
		  if(res.data.success){
			  alert("You insert Holiday successfull! ");
		   }
			
		}) 
        }
}]);


app.directive('date', function($filter) {
    return {
        restrict: 'A',
        require : 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
        	
          element.datetimepicker({
            
             format: "dd-MM-yyyy",
             viewMode: "date", 
             pickTime: false,
             autoclose: 1
              

          }).on('changeDate', function(e) {
        	  ngModelCtrl.$setViewValue(e.date.toUTCString());
            scope.$apply();
          });
        }
    };
});

//##############################################AdminMiddle##########################################

app.directive('datetimez', function($filter,$http) {
		
	    return {
	        restrict: 'A',
	        require : 'ngModel',
	        link: function(scope, element, attrs, ngModelCtrl) {
	         
	          element.datetimepicker({
	            
	            format: "MM-yyyy",
	            viewMode: "months",
	           
	            minViewMode: "months",
	            pickTime: false,
	            autoclose: true,
	          }).on('changeDate', function(e) {
	        	var MonthYear = $filter('date')(e.date,'yyyy-MM');
	        	var monthyear = $filter('date')(e.date,'MM-yyyy');
	            var month = $filter('date')(e.date,'MM');
	            var year = $filter('date')(e.date,'yyyy');
	            ngModelCtrl.$setViewValue(month);
	            ngModelCtrl.$setViewValue(year);
//	           
	            var holiday =  $filter('date')(e.date,'yyyy-MM-dd');
//	            console.log("holiday :"+ holiday );
//	            console.log("listSimpleDay length: "+ listSimpleDay.length)
	            scope.DateHoliday = {
	            		date : holiday
	            }
	            var  method = "POST";
	            var url ="api/UserController/loadHoliday";   ///-----------------------Load Holiday---------------
	            var data = scope.DateHoliday ;
//	            console.log(data);
	            $http({
	    			method:method, 
	    			url:url,
	    			data:data
	    		}).then(function(res){
//	    			console.log(res.data);
	    			var listDay = res.data;
	    			
	    			var listSimpleDay = [];
//	    			scope.varr =[];
	    			
	    			scope.isWeekend = function (date) {
//	    				console.log(listDay);
	    				
	    				for(var kk=0;kk<listDay.length;kk++)
	    					{
	    					   if(date==listDay[kk]["dayIn"])
	    						   {
//	    						     console.log(" holiday :"+date);
	    						      return true;
	    						   }
	    					}
		            	var date1 = new Date(year,month-1,date);
		            	if(date1.getDay() === 6|| date1.getDay() === 0  ){
//		            		console.log("day sun sat :"+date);
		            		return true;
		            	}
		            	else{
		            		return false;
		            	}
		            };
//		            console.log("simpleday1: "+scope.varr[0]);
	    		})
	    		
	    		var day = new Date(year, month , 0).getDate();
//	***************************************************Get Data Sent *************************************************    		
	    		var method = "POST";
	            var url = "api/UserController/loadDataSent";      //------------------Load Data Sent-------------------
	            scope.GetDataSent = {
	            		email : scope.gmail.email,
	            		date : holiday
	            }
	            var data = scope.GetDataSent;
//	            console.log(data);
	            $http({
	    			method:method, 
	    			url:url,
	    			data:data
	    		}).then(function(res){
	    			 scope.listDay = [];
	    			 scope.lists = []
		             scope.listHoliday = [];
	    			
	    			for(var k=0;k<res.data.length;k++){
	    				scope.listDay.push(res.data[k]['day']);
	    			}
	    			for(var k=0;k<res.data.length;k++)
	    			{
//	    				scope.listDay.push(res.data[k]['day']);
	    				if(res.data[k]['Features']=="Holiday")
	    					{
	    						scope.listHoliday.push(res.data[k]['day']);
	    					}
	    			}
//	    			console.log("holiday :");
//	    			console.log(scope.listHoliday);
	    			var arrayDay =0;
	    			var j1 = 0;
	    			for(var j=0;j<day;j++)
	    			{
	    				var SimpleOrHoliday = false;
	    				
	    				for(var jj=0;jj<scope.listDay.length;jj++)
	    					{
	    					    if(j+1==scope.listDay[jj])
	    					    	{
	    					    	j1=j1+1;
	    					    	SimpleOrHoliday= true;
	    					    	break;
	    					    	}
	    					}
	    				if(SimpleOrHoliday==true)
	    					{
//	    					console.log(j+" : "+res.data[j1-1]["TimeIn"]);
	    					 var list1 =  {
					            		timeIn      : res.data[j1-1]["TimeIn"],
										timeOut		: res.data[j1-1]["TimeOut"],
										hours 		: res.data[j1-1]["hours"],
										breakTime	: res.data[j1-1]["BreakTime"],
										project		: res.data[j1-1]["project"],
										descript	: res.data[j1-1]["descript"],
										timeIn_h 	: res.data[j1-1]["TimeIn"],
										timeOut_h	: res.data[j1-1]["TimeOut"],
										hours_h 	: res.data[j1-1]["hours"],
										breakTime_h	: res.data[j1-1]["BreakTime"],
										project_h	: res.data[j1-1]["project"],
										descript_h	: res.data[j1-1]["descript"]
					            	}
			            	scope.lists[j] = list1;
	    					}
	    				else{
	    					 var list1 =  {
					            		timeIn : "09:00",
										timeOut:"18:00",
										hours : "",
										breakTime: 0,
										project :"",
										descript:"",
										
											timeIn_h : "09:00",
											timeOut_h:"18:00",
											hours_h : "",
											breakTime_h: 0,
											project_h :"",
											descript_h:""
											
					            	}
			            	scope.lists[j]=list1;
	    				}
	    			}
	    			
	    		});
	    		
//	  ######################################################End data sent ###############################################  		
	    		
//	            console.log("Month Year :"+MonthYear);
	            scope.$apply(function () {
	            	scope.showtable2=true;
	                scope.number = day;
	                scope.month_year = monthyear;
	                scope.monthyear = MonthYear;
	            });

	          });
	        }
	    };
  
	});



