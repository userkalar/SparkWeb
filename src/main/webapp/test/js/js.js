
/****/
$(document).ready(function () {
    var whei = $(window).width()
    $("html").css({ fontSize: whei / 20 })
    $(window).resize(function () {
        var whei = $(window).width()
        $("html").css({ fontSize: whei / 20 })
    });
});

// 可视化图表数据
var
    left_bottom_x_data,
    left_bottom_y_data,
    center_bottom_x_data,
    center_bottom_y_data;

let
    zhuzhuangtu = [],
    right_center_title_data = [],
    right_center_data = [];
    
let
    echarts3_selected= {
        region: '',
        rentalType: ''
    },
    echarts2_selected = {
        region: '',
        houseType: ''
    },
    lastecharts_selected = {
        property: ''
    }

$(window).load(function () { $(".loading").fadeOut()})

function echarts_3(x_data,y_data) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echarts3'));


    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    color: '#dddc6b'
                }
            }
        },
        grid: {
            left: '0',
            top: '30',
            right: '20',
            bottom: '-10',
            containLabel: true
        },

        xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 14,
                },
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }

            },

            // data: ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']
            data: y_data
        }, {

            axisPointer: { show: false },
            axisLine: { show: false },
            position: 'bottom',
            offset: 20,



        }],

        yAxis: [{
            type: 'value',
            axisTick: { show: false },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }
            },
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 14,
                },
            },

            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)',

                }
            }
        }],
        series: [
            {
                name: '数目',
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 10,
                showSymbol: true,
                lineStyle: {

                    normal: {
                        color: 'rgba(228, 228, 126, 1)',
                        width: 2
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(228, 228, 126, .8)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(228, 228, 126, 0.1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#dddc6b',
                        borderColor: 'rgba(221, 220, 107, .1)',
                        borderWidth: 12
                    }
                },
                // data: [600, 200, 600, 200, 400, 200, 400, 300, 400, 300, 400, 300, 200, 400, 200]
                data: x_data
            }

        ]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}
function echarts_2(x_data,y_data) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echarts2'));

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    color: '#dddc6b'
                }
            }
        },
        grid: {
            left: '0',
            top: '30',
            right: '20',
            bottom: '-10',
            containLabel: true
        }, legend: {
            data: ['数目'],
            right: 'center',
            top: 0,
            textStyle: {
                color: "#fff"
            },
            itemWidth: 12,
            itemHeight: 10,
            // itemGap: 35
        },

        xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 14,
                },
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }

            },

            // data: ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']
            data: y_data
        }, {

            axisPointer: { show: false },
            axisLine: { show: false },
            position: 'bottom',
            offset: 20,



        }],

        yAxis: [{
            type: 'value',
            axisTick: { show: false },
            // splitNumber: 6,
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }
            },
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: 14,
                },
            },

            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }
            }
        }],
        series: [
            {
                name: '数目',
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 5,
                showSymbol: false,
                lineStyle: {

                    normal: {
                        color: 'rgba(255, 128, 128, 1)',
                        width: 2
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(255, 128, 128,.8)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(255, 128, 128, .1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#dddc6b',
                        borderColor: 'rgba(221, 220, 107, .1)',
                        borderWidth: 12
                    }
                },
                // data: [300, 100, 300, 400, 300, 400, 300, 600, 200, 400, 200, 400, 100, 300, 400]
                data: x_data
            },

        ]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}
//测试

function echarts_1(data_one,data_two,data_three) {
    var myChart = echarts.init(document.getElementById('echarts1'));
    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: { type: 'shadow' },

        },
        legend: {
            x: 'center',
            y: '0',
            icon: 'circle',
            itemGap: 8,
            textStyle: { color: 'rgba(255,255,255,.5)' },
            itemWidth: 10,
            itemHeight: 10,
        },
        grid: {
            left: '0',
            top: '30',
            right: '15',
            bottom: '0',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            // data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            data: ['Bronx','Brooklyn','Manhattan','Queens','Staten Island'],
            axisLine: { show: false },

            axisLabel: {
                textStyle: {
                    color: 'rgba(255,255,255,.6)',
                    fontSize: 14
                }
            },
        },

        yAxis: {
            type: 'value',
            splitNumber: 4,
            axisLine: { show: false },
            axisTick: { show: false },
            splitLine: {
                show: true,
                lineStyle: {
                    color: 'rgba(255,255,255,0.05)'
                }
            },
            axisLabel: {
                textStyle: {
                    color: 'rgba(255,255,255,.6)',
                    fontSize: 14
                },
            },
        },
        series: [{
            name: 'Private room',
            type: 'bar',
            barWidth: '15%',
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#8bd46e'
                    }, {
                        offset: 1,
                        color: '#03b48e'
                    }]),
                    barBorderRadius: 11,
                }

            },
            // data: [123, 154, 234, 321, 120, 390, 634, 123, 154, 234, 321, 108]
            data: data_one
        },
        {
            name:'Shared room' ,
            type: 'bar',
            barWidth: '15%',
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#3893e5'
                    }, {
                        offset: 1,
                        color: '#248ff7'
                    }]),
                    barBorderRadius: 11,
                }
            },
            // data: [63, 194, 234, 321, 278, 110, 534, 63, 194, 234, 321, 278]
            data: data_two

        },
        {
            name: 'Entire home/apt',
            type: 'bar',
            barWidth: '15%',
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#43cadd'
                    }, {
                        offset: 1,
                        color: '#0097c9'
                    }]),
                    barBorderRadius: 11,
                }
            },
            // data: [23, 354, 334, 221, 178, 190, 234, 354, 334, 221, 178, 190]
            data: data_three

        }

        ]
    };

    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });


}
function pe04(title_data,center_data) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('lastecharts'));
    option = {

        tooltip: {
            trigger: 'axis'
        },
        radar: [{
            indicator: title_data,
            textStyle: {
                color: 'red'
            },
            center: ['50%', '50%'],
            radius: '70%',
            startAngle: 90,
            splitNumber: 4,
            shape: 'circle',

            name: {
                padding: -5,
                formatter: '{value}',
                textStyle: {
                    fontSize: 14,
                    color: 'rgba(255,255,255,.6)'
                }
            },
            splitArea: {
                areaStyle: {
                    color: 'rgba(255,255,255,.05)'
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.05)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.05)'
                }
            }
        },],
        series: [{
            name: '雷达图',
            type: 'radar',
            tooltip: {
                trigger: 'item'
            },
            data: [{
                name: '床数-房屋数',
                // value: [90, 80, 20, 10, 30],
                value: center_data,
                lineStyle: {
                    normal: {
                        color: '#03b48e',
                        width: 2,
                    }
                },
                areaStyle: {
                    normal: {
                        color: '#03b48e',
                        opacity: .4
                    }
                },
                symbolSize: 0,

            },
            //  {
            //     name: '当前园区',
            //     value: [30, 20, 75, 80, 70],
            //     symbolSize: 0,
            //     lineStyle: {
            //         normal: {
            //             color: '#3893e5',
            //             width: 2,
            //         }
            //     },
            //     areaStyle: {
            //         normal: {
            //             color: 'rgba(19, 173, 255, 0.5)'
            //         }
            //     }
            // }
        ]
        },]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}
$(function () {
    initEchartsData();
    // echarts_1()
    // echarts_2()
    // pe04()

    pe01()
    pe02()
    pe03()

    function initEchartsData() {
        // 左下
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/nrpc?neighbourhood=Brooklyn&room=Private%20room',
            success: (resp) => {
                left_bottom_y_data = resp.price;
                left_bottom_x_data = resp.count;
                echarts_3(left_bottom_x_data, left_bottom_y_data);
            }
        });
        // 中下
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/nppc?neighbourhood=Brooklyn&property=Apartment',
            success: (resp) => {
                center_bottom_y_data = resp.price;
                center_bottom_x_data = resp.count;
                echarts_2(center_bottom_x_data, center_bottom_y_data);
            }
        });
        // 右中
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/bpc?property=Apartment',
            success: (resp) => {
                right_center_data = resp.count;
                let bed_data = resp.beds;
                $.each(bed_data, (value) => {
                    let item = {
                        text: value,
                        max: 20000
                    };
                    right_center_title_data.push(item);
                });
                // console.log(right_center_title_data);
                pe04(right_center_title_data, right_center_data);
            }
        });
        // 中中


               $.ajax({
                     type: 'get',
                     url: 'http://localhost:8080/nrc',
                     success: (resp) => {
                         //修改
                         right_center_data = resp;
                        /* Private_room_data =right_center_data["Private room"];
                         Shared_room_data =right_center_data["Shared room"];
                         Entire_home_apt_data =right_center_data["Entire_home/apt"];*/
                         var privateRoomData = []; // Private room数据
                         var sharedRoomData = []; // Shared room数据
                         var entireHomeData = []; // Entire home/apt数据
                         var zhuzhuangtu=[[],[],[]];
                         var privateRoomData = right_center_data["Private room"]; // Private room数据
                         var sharedRoomData = right_center_data["Shared room"]; // Shared room数据
                         var entireHomeData = right_center_data["Entire home/apt"]; // Entire home/apt数据
                         //right_center_data = resp.count;
                         // console.log(right_center_data.slice(0,5));
                         zhuzhuangtu = [
                            privateRoomData,sharedRoomData,entireHomeData
                            // [Entire_home_apt_data],[Private_room_data],[Shared_room_data ]
                             /*[
                                 "92.0",
                                 "226.0",
                                 "27.0",
                                 "150.0",
                                 "65.0"

                             ],
                             [
                                 "68.0",
                                 "206.0",
                                 "99.0",
                                 "128.0",
                                 "198.0"
                             ],
                             [
                                 "76.0",
                                 "126.0",
                                 "200.0",
                                 "58.0",
                                 "0.0"
                             ]*/
                         ]
                        // consogle.log(zhuzhuangtu[0],zhuzhuangtu[1],zhuzhuangtu[2]);

                         echarts_1(zhuzhuangtu[0],zhuzhuangtu[1],zhuzhuangtu[2]);
                    }
                 });

            }
        })


        function echarts_4() {
            var myChart = echarts.init(document.getElementById('echarts4'));
            var data = [{
                "name": "邮件总量",
                "value": 20000
            }, {
                "name": "公文交换",
                "value": 18170
            }, {
                "name": "内部流转",
                "value": 19870
            }]


            option = {

                color: ['#A0CE3A', '#31C5C0', '#1E9BD1', '#0F347B', '#585247', '#7F6AAD', '#009D85', "rgba(250,250,250,0.3)"],

                grid: {
                    bottom: 0,
                    left: 0,
                    right: '0'
                },
                series: [
                    // 主要展示层的
                    {
                        radius: ['30%', '50%'],
                        center: ['50%', '50%'],
                        type: 'pie',
                        label: {
                            normal: {
                                show: true,
                                formatter: ['{b|{b}}', '{c|{c}次}', '{d|同比：{d}%}'].join('\n'),
                                rich: {
                                    c: {
                                        color: 'rgb(241,246,104)',
                                        fontSize: 20,
                                        fontWeight: 'bold',
                                        lineHeight: 25
                                    },
                                    b: {
                                        color: 'rgb(98,137,169)',
                                        fontSize: 15,
                                        height: 40
                                    },
                                },

                                position: 'outside'
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        labelLine: {
                            normal: {
                                show: true,
                                length: 15,
                                length2: 50
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        name: "民警训练总量",
                        data: data,

                    },
                    // 边框的设置
                    {
                        radius: ['30%', '35%'],
                        center: ['50%', '50%'],
                        type: 'pie',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: false
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: false
                            }
                        },
                        animation: false,
                        tooltip: {
                            show: false
                        },
                        data: [{
                            value: 1,
                            itemStyle: {
                                color: "rgba(250,250,250,0.3)",
                            },
                        }],
                    }, {
                        name: '外边框',
                        type: 'pie',
                        clockWise: false, //顺时加载
                        hoverAnimation: false, //鼠标移入变大
                        center: ['50%', '50%'],
                        radius: ['60%', '61%'],
                        label: {
                            normal: {
                                show: false
                            }
                        },
                        data: [{
                            value: 9,
                            name: '',
                            itemStyle: {
                                normal: {
                                    borderWidth: 2,
                                    borderColor: '#0c2c69'
                                }
                            }
                        }]
                    },
                ]
            };

            myChart.setOption(option);
            window.addEventListener("resize", function () {
                myChart.resize();
            });


        }

        // function echarts_1() {
        //     var myChart = echarts.init(document.getElementById('echarts1'));
        //     option = {
        //         tooltip: {
        //             trigger: 'axis',
        //             axisPointer: { type: 'shadow' },

        //         },
        //         legend: {
        //             x: 'center',
        //             y: '0',
        //             icon: 'circle',
        //             itemGap: 8,
        //             textStyle: { color: 'rgba(255,255,255,.5)' },
        //             itemWidth: 10,
        //             itemHeight: 10,
        //         },
        //         grid: {
        //             left: '0',
        //             top: '30',
        //             right: '15',
        //             bottom: '0',
        //             containLabel: true
        //         },
        //         xAxis: {
        //             type: 'category',
        //             // data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        //             data: ['Brooklyn','Manhattan','Queens','Bronx','Staten Island'],
        //             axisLine: { show: false },

        //             axisLabel: {
        //                 textStyle: {
        //                     color: 'rgba(255,255,255,.6)',
        //                     fontSize: 14
        //                 }
        //             },
        //         },

        //         yAxis: {
        //             type: 'value',
        //             splitNumber: 4,
        //             axisLine: { show: false },
        //             axisTick: { show: false },
        //             splitLine: {
        //                 show: true,
        //                 lineStyle: {
        //                     color: 'rgba(255,255,255,0.05)'
        //                 }
        //             },
        //             axisLabel: {
        //                 textStyle: {
        //                     color: 'rgba(255,255,255,.6)',
        //                     fontSize: 14
        //                 },
        //             },
        //         },
        //         series: [{
        //             name: 'Entire home/apt',
        //             type: 'bar',
        //             barWidth: '15%',
        //             itemStyle: {
        //                 normal: {
        //                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
        //                         offset: 0,
        //                         color: '#8bd46e'
        //                     }, {
        //                         offset: 1,
        //                         color: '#03b48e'
        //                     }]),
        //                     barBorderRadius: 11,
        //                 }

        //             },
        //             // data: [123, 154, 234, 321, 120, 390, 634, 123, 154, 234, 321, 108]
        //             data: zhuzhuangtu[0]
        //         },
        //         {
        //             name: 'Private room',
        //             type: 'bar',
        //             barWidth: '15%',
        //             itemStyle: {
        //                 normal: {
        //                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
        //                         offset: 0,
        //                         color: '#3893e5'
        //                     }, {
        //                         offset: 1,
        //                         color: '#248ff7'
        //                     }]),
        //                     barBorderRadius: 11,
        //                 }
        //             },
        //             // data: [63, 194, 234, 321, 278, 110, 534, 63, 194, 234, 321, 278]
        //             data: zhuzhuangtu[1]

        //         },
        //         {
        //             name: 'Shared room',
        //             type: 'bar',
        //             barWidth: '15%',
        //             itemStyle: {
        //                 normal: {
        //                     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
        //                         offset: 0,
        //                         color: '#43cadd'
        //                     }, {
        //                         offset: 1,
        //                         color: '#0097c9'
        //                     }]),
        //                     barBorderRadius: 11,
        //                 }
        //             },
        //             // data: [23, 354, 334, 221, 178, 190, 234, 354, 334, 221, 178, 190]
        //             data: zhuzhuangtu[2]

        //         }

        //         ]
        //     };

        //     myChart.setOption(option);
        //     window.addEventListener("resize", function () {
        //         myChart.resize();
        //     });


        // }
        // function echarts_3(x_data,y_data) {
        //     // 基于准备好的dom，初始化echarts实例
        //     var myChart = echarts.init(document.getElementById('echarts3'));


        //     option = {
        //         tooltip: {
        //             trigger: 'axis',
        //             axisPointer: {
        //                 lineStyle: {
        //                     color: '#dddc6b'
        //                 }
        //             }
        //         },
        //         grid: {
        //             left: '0',
        //             top: '30',
        //             right: '20',
        //             bottom: '-10',
        //             containLabel: true
        //         },

        //         xAxis: [{
        //             type: 'category',
        //             boundaryGap: false,
        //             axisLabel: {
        //                 textStyle: {
        //                     color: "rgba(255,255,255,.6)",
        //                     fontSize: 14,
        //                 },
        //             },
        //             axisLine: {
        //                 lineStyle: {
        //                     color: 'rgba(255,255,255,.1)'
        //                 }

        //             },

        //             // data: ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']
        //             data: y_data
        //         }, {

        //             axisPointer: { show: false },
        //             axisLine: { show: false },
        //             position: 'bottom',
        //             offset: 20,


        //         }],

        //         yAxis: [{
        //             type: 'value',
        //             axisTick: { show: false },
        //             axisLine: {
        //                 lineStyle: {
        //                     color: 'rgba(255,255,255,.1)'
        //                 }
        //             },
        //             axisLabel: {
        //                 textStyle: {
        //                     color: "rgba(255,255,255,.6)",
        //                     fontSize: 14,
        //                 },
        //             },

        //             splitLine: {
        //                 lineStyle: {
        //                     color: 'rgba(255,255,255,.1)',

        //                 }
        //             }
        //         }],
        //         series: [
        //             {
        //                 name: '标题名称',
        //                 type: 'line',
        //                 smooth: true,
        //                 symbol: 'circle',
        //                 symbolSize: 10,
        //                 showSymbol: true,
        //                 lineStyle: {

        //                     normal: {
        //                         color: 'rgba(228, 228, 126, 1)',
        //                         width: 2
        //                     }
        //                 },
        //                 areaStyle: {
        //                     normal: {
        //                         color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
        //                             offset: 0,
        //                             color: 'rgba(228, 228, 126, .8)'
        //                         }, {
        //                             offset: 0.8,
        //                             color: 'rgba(228, 228, 126, 0.1)'
        //                         }], false),
        //                         shadowColor: 'rgba(0, 0, 0, 0.1)',
        //                     }
        //                 },
        //                 itemStyle: {
        //                     normal: {
        //                         color: '#dddc6b',
        //                         borderColor: 'rgba(221, 220, 107, .1)',
        //                         borderWidth: 12
        //                     }
        //                 },
        //                 // data: [600, 200, 600, 200, 400, 200, 400, 300, 400, 300, 400, 300, 200, 400, 200]
        //                 data: x_data
        //             }

        //         ]

        //     };

        //     // 使用刚指定的配置项和数据显示图表。
        //     myChart.setOption(option);
        //     window.addEventListener("resize", function () {
        //         myChart.resize();
        //     });
        // }
        function pe01() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('pe01'));
            var txt = 89
            option = {
                title: {
                    text: txt + '%',
                    x: 'center',
                    y: 'center',
                    textStyle: {
                        fontWeight: 'normal',
                        color: '#fff',
                        fontSize: '18'
                    }
                },
                color: '#49bcf7',

                series: [{
                    name: 'Line 1',
                    type: 'pie',
                    clockWise: true,
                    radius: ['65%', '80%'],
                    itemStyle: {
                        normal: {
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            }
                        }
                    },
                    hoverAnimation: false,
                    data: [{
                        value: txt,
                        name: '已使用',
                        itemStyle: {
                            normal: {
                                color: '#eaff00',
                                label: {
                                    show: false
                                },
                                labelLine: {
                                    show: false
                                }
                            }
                        }
                    }, {
                        name: '未使用',
                        value: 100 - txt
                    }]
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize", function () {
                myChart.resize();
            });
        }

        function pe02() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('pe02'));
            var txt = 6.8
            option = {
                title: {
                    text: txt + '%',
                    x: 'center',
                    y: 'center',
                    textStyle: {
                        fontWeight: 'normal',
                        color: '#fff',
                        fontSize: '18'
                    }
                },
                color: '#49bcf7',

                series: [{
                    name: 'Line 1',
                    type: 'pie',
                    clockWise: true,
                    radius: ['65%', '80%'],
                    itemStyle: {
                        normal: {
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            }
                        }
                    },
                    hoverAnimation: false,
                    data: [{
                        value: txt,
                        name: '已使用',
                        itemStyle: {
                            normal: {
                                color: '#ea4d4d',
                                label: {
                                    show: false
                                },
                                labelLine: {
                                    show: false
                                }
                            }
                        }
                    }, {
                        name: '未使用',
                        value: 100 - txt
                    }]
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize", function () {
                myChart.resize();
            });
        }

        function pe03() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('pe03'));
            var txt = 2.4
            option = {
                title: {
                    text: txt + '%',
                    x: 'center',
                    y: 'center',
                    textStyle: {
                        fontWeight: 'normal',
                        color: '#fff',
                        fontSize: '18'
                    }
                },
                color: '#49bcf7',

                series: [{
                    name: 'Line 1',
                    type: 'pie',
                    clockWise: true,
                    radius: ['65%', '80%'],
                    itemStyle: {
                        normal: {
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            }
                        }
                    },
                    hoverAnimation: false,
                    data: [{
                        value: txt,
                        name: '已使用',
                        itemStyle: {
                            normal: {
                                color: '#395ee6',
                                label: {
                                    show: false
                                },
                                labelLine: {
                                    show: false
                                }
                            }
                        }
                    }, {
                        name: '未使用',
                        value: 100 - txt
                    }
                    ]
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener("resize", function () {
                myChart.resize();
            });

        // function pe04() {
        //     // 基于准备好的dom，初始化echarts实例
        //     var myChart = echarts.init(document.getElementById('lastecharts'));
        //     option = {

        //         tooltip: {
        //             trigger: 'axis'
        //         },
        //         radar: [{
        //             indicator: [{
        //                 text: '公寓',
        //                 max: 100000
        //             }, {
        //                 text: '独栋房屋',
        //                 max: 100000
        //             }, {
        //                 text: '其他类型',
        //                 max: 100000
        //             }, {
        //                 text: '民宿',
        //                 max: 100000
        //             }, {
        //                 text: '阁楼',
        //                 max: 100000
        //             }],
        //             textStyle: {
        //                 color: 'red'
        //             },
        //             center: ['50%', '50%'],
        //             radius: '70%',
        //             startAngle: 90,
        //             splitNumber: 4,
        //             shape: 'circle',

        //             name: {
        //                 padding: -5,
        //                 formatter: '{value}',
        //                 textStyle: {
        //                     fontSize: 14,
        //                     color: 'rgba(255,255,255,.6)'
        //                 }
        //             },
        //             splitArea: {
        //                 areaStyle: {
        //                     color: 'rgba(255,255,255,.05)'
        //                 }
        //             },
        //             axisLine: {
        //                 lineStyle: {
        //                     color: 'rgba(255,255,255,.05)'
        //                 }
        //             },
        //             splitLine: {
        //                 lineStyle: {
        //                     color: 'rgba(255,255,255,.05)'
        //                 }
        //             }
        //         },],
        //         series: [{
        //             name: '雷达图',
        //             type: 'radar',
        //             tooltip: {
        //                 trigger: 'item'
        //             },
        //             data: [{
        //                 name: 'XXX',
        //                 // value: [90, 80, 20, 10, 30],
        //                 value: right_center_data,
        //                 lineStyle: {
        //                     normal: {
        //                         color: '#03b48e',
        //                         width: 2,
        //                     }
        //                 },
        //                 areaStyle: {
        //                     normal: {
        //                         color: '#03b48e',
        //                         opacity: .4
        //                     }
        //                 },
        //                 symbolSize: 0,

        //             },
        //             //  {
        //             //     name: '当前园区',
        //             //     value: [30, 20, 75, 80, 70],
        //             //     symbolSize: 0,
        //             //     lineStyle: {
        //             //         normal: {
        //             //             color: '#3893e5',
        //             //             width: 2,
        //             //         }
        //             //     },
        //             //     areaStyle: {
        //             //         normal: {
        //             //             color: 'rgba(19, 173, 255, 0.5)'
        //             //         }
        //             //     }
        //             // }
        //         ]
        //         },]
        //     };

        //     // 使用刚指定的配置项和数据显示图表。
        //     myChart.setOption(option);
        //     window.addEventListener("resize", function () {
        //         myChart.resize();
        //     });
        // }
    }

    function leftBottomRegionSelected(value) {
        echarts3_selected.region = value;
    }

    function leftBottomTypeSelected(value) {
        echarts3_selected.rentalType = value;
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/nrpc?neighbourhood=' + echarts3_selected.region + '&room=' + echarts3_selected.rentalType,
            success: (resp) => {
                left_bottom_y_data = resp.price;
                left_bottom_x_data = resp.count;
                echarts_3(left_bottom_x_data, left_bottom_y_data);
            }
        })
    }

    function centerBottomRegionSelected(value) {
        echarts2_selected.region = value;
    }

    function centerBottomTypeSelected(value) {
        echarts2_selected.houseType = value;
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/nppc?neighbourhood=' + echarts2_selected.region + '&property=' + echarts2_selected.houseType,
            success: (resp) => {
                left_bottom_y_data = resp.price;
                left_bottom_x_data = resp.count;
                echarts_2(left_bottom_x_data, left_bottom_y_data);
            }
        })
    }

    function rightCenterPropertySelected(value) {
        lastecharts_selected.property = value;
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/bpc?property=' + lastecharts_selected.property,
            success: (resp) => {
                right_center_data = resp.count;
                right_center_title_data.length = 0;
                console.log(right_center_title_data);
                $.each(resp.beds, (value) => {
                    let item = {
                        text: value,
                        max: 20000
                    };
                    right_center_title_data.push(item);
                });
                console.log(right_center_title_data);
                pe04(right_center_title_data, right_center_data);
            }
        })
    }


