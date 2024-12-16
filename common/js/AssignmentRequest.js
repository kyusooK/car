let rowData = [];
$(document).ready(function(){
    var OPT = {
        "Cfg": {
            "SearchMode": 2,
            "HeaderMerge": 3,
            "MessageWidth": 300,
        },
        "Def": {
            "Row": {
            "CanFormula": true
            }
        },
        Cols:[
            {"Header": "No", "Name": "No", "Type": "Int", "Align": "Center", "Width":140, "CanEdit":0},
            {"Header": "requestStatus", "Name": "requestStatus", "Type": "","|PENDING|APPROVED|REJECTED|COMPLETED""|PENDING|APPROVED|REJECTED|COMPLETED" "Align": "Center", "Width":140, "CanEdit":1},  
            {"Header": "purpose", "Name": "purpose", "Type": "Text", "Align": "Center", "Width":140, "CanEdit":1},  
            {"Header": "usageType", "Name": "usageType", "Type": "","|BUSINESS_SUPPORT|EXTERNAL_ACTIVITY""|BUSINESS_SUPPORT|EXTERNAL_ACTIVITY" "Align": "Center", "Width":140, "CanEdit":1},  
            {"Header": "numberOfPassengers", "Name": "numberOfPassengers", "Type": "Int", "Align": "Center", "Width":140, "CanEdit":1},  
            {"Header": "contactNumber", "Name": "contactNumber", "Type": "Text", "Align": "Center", "Width":140, "CanEdit":1},  
            {"Header": "remarks", "Name": "remarks", "Type": "Text", "Align": "Center", "Width":140, "CanEdit":1},  
            {"Header": ["applicant", "Name"], "Name": "Name", "Type": "Text", "Width": 140, "CanEdit": 1},
            {"Header": ["applicant", "Department"], "Name": "Department", "Type": "Text", "Width": 140, "CanEdit": 1},
            {"Header": ["applicant", "EmployeeId"], "Name": "EmployeeId", "Type": "Text", "Width": 140, "CanEdit": 1},
            {"Header": ["applicant", "PhoneNumber"], "Name": "PhoneNumber", "Type": "Text", "Width": 140, "CanEdit": 1},
            {"Header": ["applicant", "MobileNumber"], "Name": "MobileNumber", "Type": "Text", "Width": 140, "CanEdit": 1},
            {"Header": ["applicant", "ApplicationDate"], "Name": "ApplicationDate", "Type": "Date", "Width": 140, "CanEdit": 1},
            {"Header": ["approvalInfo", "ApproverName"], "Name": "ApproverName", "Type": "Text", "Width": 140, "CanEdit": 1},
            {"Header": ["approvalInfo", "ApproverPosition"], "Name": "ApproverPosition", "Type": "Text", "Width": 140, "CanEdit": 1},
            {"Header": ["operationPeriod", "StartDate"], "Name": "StartDate", "Type": "Date", "Width": 140, "CanEdit": 1},
            {"Header": ["operationPeriod", "EndDate"], "Name": "EndDate", "Type": "Date", "Width": 140, "CanEdit": 1},
            {"Header": ["file", "fileName"], "Name": "fileName", "Type": "Text", "Width": 140, "CanEdit": 1},
            {"Header": ["file", "file"], "Name": "file", "Type": "Text", "Width": 140, "CanEdit": 1},
        ],
        Events: {
            onClick: function(evtParam) {
                var originalRowData = rowData.find(item => item.No === evtParam.row.No);
                var detailData = [];
        
                detailSheet.loadSearchData(detailData);
            }
        }
    };
    
    var detailSheetOptions = {
        "Cfg": {
            "SearchMode": 2,
            "HeaderMerge": 3,
            "MessageWidth": 300,
        },
        Cols:[
        ]
    };

    IBSheet.create({
       id:"sheet",
       el:"sheet_DIV",
       options: OPT
    });
    
    IBSheet.create({
        id:"detailSheet",
        el:"detailSheet_DIV",
        options: detailSheetOptions
    });
   
});

function retrieve(){
    fetch("/assignmentRequests", {
        method: 'GET',
        headers: {
            "Cache-Control": "no-cache",
            "Pragma": "no-cache",
            "Content-Type": "application/json"
        }
    }).then(res => {
        return res.json();
    }).then(json => {
        for(var i = 0; i < json.length; i++){
            json[i].No = json[i].requestId
            
            
            
            

        }
        
        rowData = json;
        sheet.loadSearchData(json)
    }).catch(error => {
        console.error("에러", error);
    });
}

function addData(){
   sheet.addRow();
}

function deleteData(){
    sheet.deleteRow(sheet.getFocusedRow());
}

function save(data){
    var rows = data;
    rows.requestId = rows.No
    delete rows.No

    $.ajax({
        url: "/assignmentRequests",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(rows)
    });
    retrieve();

}

function saveRow(){
    var rows = sheet.getSaveJson()?.data;
    for(var i = 0; i < rows.length; i++){
        rows[i].requestId = rows[i].No
        delete rows[i].No

        
        
        
        
    }
    
    rowData = rows;

    for(var i=0; i<rows.length;i++){
        switch(rows[i].STATUS){
            case "Changed":
                var rowObj = sheet.getRowById(rows[i].id);
                var changedData = JSON.parse(sheet.getChangedData(rowObj))["Changes"][0];
                changedData.id = rows[i].requestId
                var id = changedData.id 
                $.ajax({
                    url: `/assignmentRequests/${id}`,
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(changedData)
                });
                break;
            case "Deleted":
                var id = rows[i].requestId
                $.ajax({
                    url: `/assignmentRequests/${id}`,
                    method: "DELETE",
                });
                break;
        }     
    }           
}
function submitUpdateAssignmentRequest(data){
    const id = data.requestId;
    fetch(`/assignmentRequests/updateAssignmentRequest/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
        alert(error);
    });
}           
function submitCancelAssignmentRequest(data){
    const id = data.requestId;
    fetch(`/assignmentRequests/cancelAssignmentRequest/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
        alert(error);
    });
}           
function searchResult(params) {
    const allEmpty = !params.requestId &&!params.applicantName &&!params.vehicleType &&!params.operationPeriod &&!params.status ;
    
    if (allEmpty) {
        alert("검색할 내용을 입력하세요.");
        return;
    }
    const queryParams = new URLSearchParams(params).toString();

    $.ajax({
        url: `/assignmentRequests?${queryParams}`,
        method: 'GET',
        headers: {
            "Cache-Control": "no-cache",
            "Pragma": "no-cache",
            "Content-Type": "application/json"
        },
        success: function(results) {
            if (results.length > 0) {
                sheet.loadSearchData(results);
            } else {
                alert("해당 조건에 대한 결과가 없습니다.");
            }
        },
        error: function(xhr, status, error) {
            console.error("에러", error);
            alert("데이터를 가져오는 중 오류가 발생했습니다.");
        }
    });
}
function searchResult(params) {
    const allEmpty = !params.requestId &&!params.applicant &&!params.approvalInfo &&!params.purpose &&!params.usageType &&!params.operationPeriod &&!params.vehicleType &&!params.numberOfPassengers &&!params.driverIncluded &&!params.contactNumber &&!params.remarks &&!params.attachedDocuments ;
    
    if (allEmpty) {
        alert("검색할 내용을 입력하세요.");
        return;
    }
    const queryParams = new URLSearchParams(params).toString();

    $.ajax({
        url: `/assignmentRequests?${queryParams}`,
        method: 'GET',
        headers: {
            "Cache-Control": "no-cache",
            "Pragma": "no-cache",
            "Content-Type": "application/json"
        },
        success: function(results) {
            if (results.length > 0) {
                sheet.loadSearchData(results);
            } else {
                alert("해당 조건에 대한 결과가 없습니다.");
            }
        },
        error: function(xhr, status, error) {
            console.error("에러", error);
            alert("데이터를 가져오는 중 오류가 발생했습니다.");
        }
    });
}
