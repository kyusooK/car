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
            {"Header": "status", "Name": "status", "Type": "", "Align": "Center", "Width":140, "CanEdit":1},  
            {"Header": "remarks", "Name": "remarks", "Type": "Text", "Align": "Center", "Width":140, "CanEdit":1},  
            {"Header": ["assignmentRequestReference", "ApplicantName"], "Name": "ApplicantName", "Type": "Text", "Width": 140, "CanEdit": 1},
            {"Header": ["assignmentRequestReference", "VehicleType"], "Name": "VehicleType",  "Width": 140, "CanEdit": 1},
            {"Header": ["assignmentRequestReference", "NumberOfPassengers"], "Name": "NumberOfPassengers", "Type": "Int", "Width": 140, "CanEdit": 1},
            {"Header": ["assignmentRequestReference", "DriverIncluded"], "Name": "DriverIncluded",  "Width": 140, "CanEdit": 1},
            {"Header": ["operationPeriod", "StartDate"], "Name": "StartDate", "Type": "Date", "Width": 140, "CanEdit": 1},
            {"Header": ["operationPeriod", "EndDate"], "Name": "EndDate", "Type": "Date", "Width": 140, "CanEdit": 1},
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
    fetch("/assignmentRecords", {
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
            json[i].No = json[i].recordId
            
            

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
    rows.recordId = rows.No
    delete rows.No

    $.ajax({
        url: "/assignmentRecords",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(rows)
    });
    retrieve();

}

function saveRow(){
    var rows = sheet.getSaveJson()?.data;
    for(var i = 0; i < rows.length; i++){
        rows[i].recordId = rows[i].No
        delete rows[i].No

        
        
    }
    
    rowData = rows;

    for(var i=0; i<rows.length;i++){
        switch(rows[i].STATUS){
            case "Changed":
                var rowObj = sheet.getRowById(rows[i].id);
                var changedData = JSON.parse(sheet.getChangedData(rowObj))["Changes"][0];
                changedData.id = rows[i].recordId
                var id = changedData.id 
                $.ajax({
                    url: `/assignmentRecords/${id}`,
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(changedData)
                });
                break;
            case "Deleted":
                var id = rows[i].recordId
                $.ajax({
                    url: `/assignmentRecords/${id}`,
                    method: "DELETE",
                });
                break;
        }     
    }           
}
function submitUpdateAssignmentRecord(data){
    const id = data.recordId;
    fetch(`/assignmentRecords/updateAssignmentRecord/${id}`, {
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
function submitCancelAssignmentRecord(data){
    const id = data.recordId;
    fetch(`/assignmentRecords/cancelAssignmentRecord/${id}`, {
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
    const allEmpty = !params.recordId &&!params.applicantName &&!params.vehicleType &&!params.operationPeriod &&!params.status ;
    
    if (allEmpty) {
        alert("검색할 내용을 입력하세요.");
        return;
    }
    const queryParams = new URLSearchParams(params).toString();

    $.ajax({
        url: `/assignmentRecords?${queryParams}`,
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
    const allEmpty = !params.recordId &&!params.assignmentRequestReference &&!params.operationPeriod &&!params.usagePurpose &&!params.status &&!params.remarks ;
    
    if (allEmpty) {
        alert("검색할 내용을 입력하세요.");
        return;
    }
    const queryParams = new URLSearchParams(params).toString();

    $.ajax({
        url: `/assignmentRecords?${queryParams}`,
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
