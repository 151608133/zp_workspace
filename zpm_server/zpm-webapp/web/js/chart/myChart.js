var chart;
function myChart(containerId, obj, funcCode) {

	this.containerId = containerId;
	var chartWidth = obj['chartWidth'];
	var chartHight = obj['chartHight'];
	var chartText = obj['chartText'];
	var xCategories = obj['xCategories'];
	var xText = obj['xText'];
	var yText = obj['yText'];
	var seriesName = obj['seriesName'];
	var chartType = obj['chartType'];
	var functionCode = funcCode;

	/**
	 * 折线图
	 */
	if (chartType == "line") {
		$(function() {
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart : {
						renderTo : containerId,
						width : chartWidth,
						hight : chartHight,
						type : chartType
					},
					lang:{ 
						exportButtonTitle: '导出',
						printButtonTitle: '打印',
						downloadJPEG:"下载JPEG 图片",
						downloadPDF: "下载PDF文档",
						downloadPNG: "下载PNG 图片",
						downloadSVG: "下载SVG 矢量图"
					},
					credits : {
						enabled : false
					},
					title : {
						text : chartText
					},
					xAxis : {
						categories : xCategories,
						labels : {
							align : 'center',
							formatter : function() {
								return this.value;
							},
							rotation : 10,
							staggerLines : 1
						},
						tickInterval : 1,
						title : {
							text : xText
						}
					},
					yAxis : {
						labels : {
							align : 'right',
							formatter : function() {
								return this.value;
							}
						},
						tickInterval : 3,
						title : {
							text : yText
						}
					},
					//是否有竖线
					/* tooltip: {
		                   crosshairs: true
					 },*/
					plotOptions : {
						series : {
							cursor : 'pointer',
							events : {
								legendItemClick : false
							},
							pointWidth : 30
						},
						line : {
							// 允许线性图上的数据点进行点击
							allowPointSelect : true,
							// 数据点的点击事件
							events : {
								click : functionCode
							},
							// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
							point : {
								events : {
									click : functionCode
								}
							},
							// 是否在图注中显示。
							showInLegend : true,
							// 调整图像顺序关系
							zIndex : 3
						}
					},
					series : [ {
						type : chartType,
						name : seriesName,
						data : []
					} ]
				});

			});
		});
	};

	/**
	 * 纵向柱形图
	 */
if (chartType == "column") {
	$(function() {
		$(document).ready(function() {
			chart = new Highcharts.Chart({
				chart : {
					renderTo : containerId,
					width : chartWidth,
					hight : chartHight,
					type : chartType
				},
				lang:{ 
					exportButtonTitle: '导出',
					printButtonTitle: '打印',
					downloadJPEG:"下载JPEG 图片",
					downloadPDF: "下载PDF文档",
					downloadPNG: "下载PNG 图片",
					downloadSVG: "下载SVG 矢量图"
				},
				credits : {
					enabled : false
				},
				title : {
					text : chartText
				},
				xAxis : {
					categories : xCategories,
					labels : {
						align : 'center',
						formatter : function() {
							return this.value;
						},
						rotation : 10,
						staggerLines : 1
					},
					tickInterval : 1,
					title : {
						text : xText
					}
				},
				yAxis : {
					labels : {
						align : 'right',
						formatter : function() {
							return this.value;
						}
					},
					tickInterval : 3,
					title : {
						text : yText
					}
				},
				plotOptions : {
					series : {
						cursor : 'pointer',
						events : {
							legendItemClick : false
						},
						pointWidth : 30
					},
					dataLabels : {
						enabled : true
					},
					column : {
						// 允许线性图上的数据点进行点击
						allowPointSelect : true,
						// 数据点的点击事件
						events : {
							click : functionCode
						},
						// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
						point : {
							events : {
								click : functionCode
							}
						},
						// 是否在图注中显示。
						showInLegend : true,
						// 调整图像顺序关系
						zIndex : 3
					}
				},
				series : [ {
					type : chartType,
					name : seriesName,
					data : []
				} ]
			});
		});
	});
}


/**
 * 曲线图
 */
if (chartType == "spline") {
	$(function() {
		$(document).ready(function() {
			chart = new Highcharts.Chart({
				chart : {
					renderTo : containerId,
					width : chartWidth,
					hight : chartHight,
					type : chartType
				},
				lang:{ 
					exportButtonTitle: '导出',
					printButtonTitle: '打印',
					downloadJPEG:"下载JPEG 图片",
					downloadPDF: "下载PDF文档",
					downloadPNG: "下载PNG 图片",
					downloadSVG: "下载SVG 矢量图"
				},
				credits : {
					enabled : false
				},
				title : {
					text : chartText
				},
				xAxis : {
					categories : xCategories,
					labels : {
						align : 'center',
						formatter : function() {
							return this.value;
						},
						rotation : 10,
						staggerLines : 1
					},
					tickInterval : 1,
					title : {
						text : xText
					}
				},
				yAxis : {
					labels : {
						align : 'right',
						formatter : function() {
							return this.value;
						}
					},
					tickInterval : 3,
					title : {
						text : yText
					}
				},
				plotOptions : {

					series : {
						cursor : 'pointer',
						events : {
							legendItemClick : false
						},
						pointWidth : 30
					},
					spline : {
						// 允许线性图上的数据点进行点击
						allowPointSelect : true,
						// 数据点的点击事件
						events : {
							click : functionCode
						},
						// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
						point : {
							events : {
								click : functionCode
							}
						},
						// 是否在图注中显示。
						showInLegend : true,
						// 调整图像顺序关系
						zIndex : 3
					}

				},
				series : [ {
					type : chartType,
					name : seriesName,
					data : []
				} ]
			});

		});
	});
}


/**
 * 饼型图
 */
if (chartType == "pie") {
	$(function() {
		$(document).ready(function() {
			chart = new Highcharts.Chart({
				chart : {
					renderTo : containerId,
					width : chartWidth,
					hight : chartHight,
					type : chartType
				},
				lang:{ 
					exportButtonTitle: '导出',
					printButtonTitle: '打印',
					downloadJPEG:"下载JPEG 图片",
					downloadPDF: "下载PDF文档",
					downloadPNG: "下载PNG 图片",
					downloadSVG: "下载SVG 矢量图"
				},
				credits : {
					enabled : false
				},
				title : {
					text : chartText
				},
				xAxis : {
					categories : xCategories,
					labels : {
						align : 'center',
						formatter : function() {
							return this.value;
						},
						rotation : 10,
						staggerLines : 1
					},
					tickInterval : 1,
					title : {
						text : xText
					}
				},
				yAxis : {
					labels : {
						align : 'right',
						formatter : function() {
							return this.value;
						}
					},
					tickInterval : 3,
					title : {
						text : yText
					}
				},
				tooltip: {
					formatter: function() {
			            return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+
			                         Highcharts.numberFormat(this.y, 0, ',') +yText+' )';
			         },
			         useHTML:true
	            },
				plotOptions : {
					dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                        }
                    },
                    pie:{
                        // 是否允许扇区点击
                        allowPointSelect: true,
                        // 点击后，滑开的距离
                        slicedOffset: 5,
                        // 饼图的中心坐标
                        // 饼图的大小
                        // 数据标签
                        dataLabels: {
                            // 是否允许标签
                            enabled: true,
                            // 标签与图像元素之间的间距
                            distance: 10
                        },
                        // 数据点的点击事件
                        events:{
                            click: functionCode
                        },
                        // 是否忽略隐藏的项
                        ignoreHiddenPoint: true,
                        // 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
                        point:{
                            events:{
                                click: functionCode
                            }
                        },
                        // 是否在图注中显示。
                        showInLegend: true,
                        // 调整图像顺序关系
                        zIndex: 0
                    }
                },    
				series : [ {
                    type: chartType,
                    name: chartText,
                    data: []
                } ]
			});

		});
	});
}


/**
 * 横向条形图
 */
if (chartType == "bar") {
	$(function() {
		$(document).ready(function() {

			chart = new Highcharts.Chart({

				chart : {
					renderTo : containerId,
					width : chartWidth,
					hight : chartHight,
					type : chartType
				},
				lang:{ 
					exportButtonTitle: '导出',
					printButtonTitle: '打印',
					downloadJPEG:"下载JPEG 图片",
					downloadPDF: "下载PDF文档",
					downloadPNG: "下载PNG 图片",
					downloadSVG: "下载SVG 矢量图"
				},
				credits : {
					enabled : false
				},

				title : {
					text : chartText
				},

				xAxis : {
					categories : xCategories,
					labels : {
						align : 'center',
						formatter : function() {
							return this.value;
						},
						rotation : 10,
						staggerLines : 1
					},
					tickInterval : 1,
					title : {
						text : xText
					}
				},

				yAxis : {
					labels : {
						align : 'right',
						formatter : function() {
							return this.value;
						}
					},
					tickInterval : 3,
					title : {
						text : yText
					}
				},

				plotOptions : {

					series : {
						cursor : 'pointer',
						events : {
							legendItemClick : false
						},
						pointWidth : 30
					},

					bar : {
						// 允许线性图上的数据点进行点击
						allowPointSelect : true,
						// 数据点的点击事件
						events : {
							click : functionCode
						},
						// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
						point : {
							events : {
								click : functionCode
							}
						},
						// 是否在图注中显示。
						showInLegend : true,
						// 调整图像顺序关系
						zIndex : 3
					}

				},

				series : [ {
					type : chartType,
					name : seriesName,
					data : []
				} ]
			});

		});
	});
}

/**
 * 散播型
 */
if (chartType == "scatter") {
	$(function() {
		$(document).ready(function() {

			chart = new Highcharts.Chart({

				chart : {
					renderTo : containerId,
					width : chartWidth,
					hight : chartHight,
					type : chartType
				},
				lang:{ 
					exportButtonTitle: '导出',
					printButtonTitle: '打印',
					downloadJPEG:"下载JPEG 图片",
					downloadPDF: "下载PDF文档",
					downloadPNG: "下载PNG 图片",
					downloadSVG: "下载SVG 矢量图"
				},
				credits : {
					enabled : false
				},

				title : {
					text : chartText
				},

				xAxis : {
					categories : xCategories,
					labels : {
						align : 'center',
						formatter : function() {
							return this.value;
						},
						rotation : 10,
						staggerLines : 1
					},
					tickInterval : 1,
					title : {
						text : xText
					}
				},

				yAxis : {
					labels : {
						align : 'right',
						formatter : function() {
							return this.value;
						}
					},
					tickInterval : 3,
					title : {
						text : yText
					}
				},

				plotOptions : {

					series : {
						cursor : 'pointer',
						events : {
							legendItemClick : false
						},
						pointWidth : 30
					},

					scatter : {
						// 允许线性图上的数据点进行点击
						allowPointSelect : true,
						// 数据点的点击事件
						events : {
							click : functionCode
						},
						// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
						point : {
							events : {
								click : functionCode
							}
						},
						// 是否在图注中显示。
						showInLegend : true,
						// 调整图像顺序关系
						zIndex : 3
					}

				},

				series : [ {
					type : chartType,
					name : seriesName,
					data : []
				} ]
			});

		});
	});
}

/**
 * 折线区域图
 */
if (chartType == "area") {
	$(function() {
		$(document).ready(function() {

			chart = new Highcharts.Chart({

				chart : {
					renderTo : containerId,
					width : chartWidth,
					hight : chartHight,
					type : chartType
				},
				lang:{ 
					exportButtonTitle: '导出',
					printButtonTitle: '打印',
					downloadJPEG:"下载JPEG 图片",
					downloadPDF: "下载PDF文档",
					downloadPNG: "下载PNG 图片",
					downloadSVG: "下载SVG 矢量图"
				},
				credits : {
					enabled : false
				},

				title : {
					text : chartText
				},

				xAxis : {
					categories : xCategories,
					labels : {
						align : 'center',
						formatter : function() {
							return this.value;
						},
						rotation : 10,
						staggerLines : 1
					},
					tickInterval : 1,
					title : {
						text : xText
					}
				},

				yAxis : {
					labels : {
						align : 'right',
						formatter : function() {
							return this.value;
						}
					},
					tickInterval : 3,
					title : {
						text : yText
					}
				},

				plotOptions : {

					series : {
						cursor : 'pointer',
						events : {
							legendItemClick : false
						},
						pointWidth : 30
					},

					area : {
						// 允许线性图上的数据点进行点击
						allowPointSelect : true,
						// 数据点的点击事件
						events : {
							click : functionCode
						},
						// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
						point : {
							events : {
								click : functionCode
							}
						},
						// 是否在图注中显示。
						showInLegend : true,
						// 调整图像顺序关系
						zIndex : 3
					}

				},

				series : [ {
					type : chartType,
					name : seriesName,
					data : []
				} ]
			});

		});
	});
}

/**
 * 曲线区域图
 */

if (chartType == "areaspline") {
	$(function() {
		$(document).ready(function() {

			chart = new Highcharts.Chart({

				chart : {
					renderTo : containerId,
					width : chartWidth,
					hight : chartHight,
					type : chartType
				},
				lang:{ 
					exportButtonTitle: '导出',
					printButtonTitle: '打印',
					downloadJPEG:"下载JPEG 图片",
					downloadPDF: "下载PDF文档",
					downloadPNG: "下载PNG 图片",
					downloadSVG: "下载SVG 矢量图"
				},
				credits : {
					enabled : false
				},

				title : {
					text : chartText
				},

				xAxis : {
					categories : xCategories,
					labels : {
						align : 'center',
						formatter : function() {
							return this.value;
						},
						rotation : 10,
						staggerLines : 1
					},
					tickInterval : 1,
					title : {
						text : xText
					}
				},

				yAxis : {
					labels : {
						align : 'right',
						formatter : function() {
							return this.value;
						}
					},
					tickInterval : 3,
					title : {
						text : yText
					}
				},

				plotOptions : {

					series : {
						cursor : 'pointer',
						events : {
							legendItemClick : false
						},
						pointWidth : 30
					},

					areaspline : {
						// 允许线性图上的数据点进行点击
						allowPointSelect : true,
						// 数据点的点击事件
						events : {
							click : functionCode
						},
						// 当具体的数据点被点击时的事件响应函数。如果不需要事件响应，可以删除。
						point : {
							events : {
								click : functionCode
							}
						},
						// 是否在图注中显示。
						showInLegend : true,
						// 调整图像顺序关系
						zIndex : 3
					}

				},

				series : [ {
					type : chartType,
					name : seriesName,
					data : []
				} ]
			});

		});
	});
}
};



function MyData(name,y){
	this.name=name;
	this.y=y;
}


function setData(data) {
	var a = new Array();
	if(data['chartData']!=null && data['chartData'].length>0){
		var xCategories = data['xCategories'];
		var chartData = data['chartData'];
		for(var i = 0;i<xCategories.length;i++){
			var b = new MyData(xCategories[i],chartData[i]);
			a.push(b);
		};
	}
	chart.series[0].setData(a);
};
