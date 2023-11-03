/**
 * 
 */
 
window.onload = function () {
    donutBussChartDraw();
    donutIssueChartDraw();
    stackBussChartDraw();
    stackIssueChartDraw();
}
 

//---------------------- Business Doughnut Chart Start ------
let pieBussChartData = {
	
    labels: ['완료', '미완료', '초과완료'],
    datasets: [{
        data: [projectStatus.bussCmplCnt, projectStatus.bussUncmplCnt, projectStatus.bussExceCnt],
        backgroundColor: ['rgb(255, 99, 132)', 'rgb(255, 159, 64)', 'rgb(255, 205, 86)']
    }] 
};

let donutBussChartDraw = function() {
    let ctx = document.getElementById('bussDonutChart').getContext('2d');
    
    window.pieChart = new Chart(ctx, {
        type: 'doughnut',
        data: pieBussChartData,
        options: {
            responsive: false,
	        legend : {
	        	display:false,
	        	position:'right'
	        }
        }
    });
};
//---------------------- Business Doughnut Chart End ------

let barOption = {
    	plugins:{
    		stacked100 :{
    			enable: true
    		}
    	},
        responsive: false,
        scales: {
            xAxes: [{
                stacked: true
            }],
            yAxes: [{
                stacked: true
            }]
        },
        legend : {
        	display:true,
        	position:'right'
        }
    }

// ----------------------Business Bar Chart Start -------
let stackBussChartDraw = function(){
	
	var stctx = document.getElementById("bussStBar");
    window.stBar = new Chart(stctx, { 
		type: 'horizontalBar',
        data: { 
            labels: ["프로젝트", "회사(프로젝트 완료시점)","회사(현재)"],
            datasets: [{ 
                label: '완료', 
                backgroundColor: 'rgb(255, 99, 132)', 
                data: [projectStatus.bussCmplRate,
                	cpnStatForPrjtDt.mmBussCmplRate,
                	cpnStatForCurrDt.mmBussCmplRate], 
            }, { 
                label: '미완료', 
                backgroundColor: 'rgb(255, 159, 64)', 
                data: [projectStatus.bussUncmplRate,
                	cpnStatForPrjtDt.mmBussUncmplRate,
                	cpnStatForCurrDt.mmBussUncmplRate], 
            }, { 
                label: '초과완료', 
                backgroundColor: 'rgb(255, 205, 86)', 
                data: [projectStatus.bussExceRate,
                	cpnStatForPrjtDt.mmBussExcecmplRate,
                	cpnStatForCurrDt.mmBussExcecmplRate], 
            }], 
        }, 
        options: barOption
    });
}

//----------------------Business Bar Chart End -------

//----------------------Issue Doughnut Chart Start
let pieIssuChartData = {
	
    labels: ['해결', '미해결', '해결불가'],
    datasets: [{
        data: [projectStatus.issuSolveCnt, projectStatus.issuUnsolveCnt, projectStatus.issuUnsolvableCnt],
        backgroundColor: ['rgb(255, 99, 132)', 'rgb(255, 159, 64)', 'rgb(255, 205, 86)']
    }] 
};

let donutIssueChartDraw = function() {
    let ctx = document.getElementById('issuDonutChart').getContext('2d');
    
    window.pieChart = new Chart(ctx, {
        type: 'doughnut',
        data: pieIssuChartData,
        options: {
            responsive: false,
	        legend : {
	        	display:false,
	        	position:'right'
	        }
        }
    });
};

//----------------------Issue Doughnut Chart Start End

// ----------------------Issue Bar Chart Start -------
let stackIssueChartDraw = function(){
	
	var stctx = document.getElementById("issuStBar");
    window.stBar = new Chart(stctx, { 
		type: 'horizontalBar',
        data: { 
            labels: ["프로젝트", "회사(프로젝트 완료시점)","회사(현재)"],
            datasets: [{ 
                label: '완료', 
                backgroundColor: 'rgb(255, 99, 132)', 
                data: [projectStatus.issuSolveRate,
                	cpnStatForPrjtDt.mmIssuSolveRate,
                	cpnStatForCurrDt.mmIssuSolveRate], 
            }, { 
                label: '미완료', 
                backgroundColor: 'rgb(255, 159, 64)', 
                data: [projectStatus.issuUnsolveRate,
                	cpnStatForPrjtDt.mmIssuUnsolveRate,
                	cpnStatForCurrDt.mmIssuUnsolveRate], 
            }, { 
                label: '초과완료', 
                backgroundColor: 'rgb(255, 205, 86)', 
                data: [projectStatus.issuUnsolvableRate,
                	cpnStatForPrjtDt.mmIssuUnsolvableRate,
                	cpnStatForCurrDt.mmIssuUnsolvableRate], 
            }], 
        }, 
        options: barOption
    });
}