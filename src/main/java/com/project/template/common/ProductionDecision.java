package com.project.template.common;

public class ProductionDecision {

    // 成本常量
    static final int PART1_COST = 4;
    static final int PART1_DETECT_COST = 0;
    static final int PART2_COST = 18;
    static final int PART2_DETECT_COST = 1;
    static final int ASSEMBLY_COST = 6;
    static final int PRODUCT_DETECT_COST = 2;
    static final int MARKET_SALE = 56;
    static final int EXCHANGE_COST = 10;
    static final int DISMANTLE_COST = 5;

    // 次品率常量
    static final double PART1_DEFECT_RATE = 0.10;
    static final double PART2_DEFECT_RATE = 0.20;
    static final double PRODUCT_DEFECT_RATE = 0.10;
    //零件数取10个
    static double num1=10;
    static double num2=10;

    static double income=0;
    static double badProduct=0;

    public static void main(String[] args) {
        double[][][][] dp = new double[2][2][2][2];

        // 遍历每种检测和拆解组合
        for (int part1Detect = 0; part1Detect <= 1; part1Detect++) {
            for (int part2Detect = 0; part2Detect <= 1; part2Detect++) {
                for (int productDetect = 0; productDetect <= 1; productDetect++) {
                    for (int dismantle = 0; dismantle <= 1; dismantle++) { // 拆解决策
                        //init
                        income=0;num1=10;num2=10;;badProduct=0;
                        //直接计算利润
                        dp[part1Detect][part2Detect][productDetect][dismantle] = -calculateCost(part1Detect, part2Detect, productDetect, dismantle)+income;
                    }
                }
            }
        }

        // 找到最优决策
        double maxInCome = Double.MIN_VALUE;
        int bestPart1Detect = 0, bestPart2Detect = 0, bestProductDetect = 0, bestDismantle = 0;
        for (int part1Detect = 0; part1Detect <= 1; part1Detect++) {
            for (int part2Detect = 0; part2Detect <= 1; part2Detect++) {
                for (int productDetect = 0; productDetect <= 1; productDetect++) {
                    for (int dismantle = 0; dismantle <= 1; dismantle++) {
                        if (dp[part1Detect][part2Detect][productDetect][dismantle] > maxInCome) {
                            maxInCome = dp[part1Detect][part2Detect][productDetect][dismantle];
                            bestPart1Detect = part1Detect;
                            bestPart2Detect = part2Detect;
                            bestProductDetect = productDetect;
                            bestDismantle = dismantle;
                        }
                    }
                }
            }
        }

        // 输出最优决策方案
        System.out.println("最优方案: 检测零配件1 = " + (bestPart1Detect == 1) +
                ", 检测零配件2 = " + (bestPart2Detect == 1) +
                ", 检测成品 = " + (bestProductDetect == 1) +
                ", 对不合格成品拆解 = " + (bestDismantle == 1));
        System.out.println("最大利润: " + maxInCome);
    }

    // 计算成本函数
    static double calculateCost(int part1Detect, int part2Detect, int productDetect, int dismantle) {

        double part1Cost = PART1_COST * num1;
        double part2Cost = PART2_COST * num2;

        // 零配件1检测成本
        if (part1Detect == 1) {
            part1Cost += PART1_DETECT_COST*num1;
            //零件1的数量变化
            num1 = (num1 * (1 - PART1_DEFECT_RATE));
        }else{
            part1Cost+=PART1_DEFECT_RATE*num1*DISMANTLE_COST;
        }

        // 零配件2检测成本
        if (part2Detect == 1) {
            part2Cost += PART2_DETECT_COST*10;
            //零件2的数量变化
            num2 = (num2 * (1 - PART2_DEFECT_RATE));
        }else{
            part2Cost+=PART2_DEFECT_RATE*num2*DISMANTLE_COST;
        }

        //可生产的成品数量
        double totalNum=Math.min(num1,num2);//取最小值
        //多余的零件
        num1-=totalNum;
        num2-=totalNum;
        //可回收成本
        double recoverCost=num1*PART1_COST+num2*PART2_COST;

        // 成品检测和市场阶段
        double productCost = ASSEMBLY_COST*totalNum;//组装成本

        if (productDetect == 1) {//检测成品
            productCost += PRODUCT_DETECT_COST*totalNum;
            // 如果检测出不合格的成品，根据是否拆解决策
            productCost += PRODUCT_DEFECT_RATE * (dismantle == 1 ? DISMANTLE_COST : 0)*totalNum;
            double tempNum=totalNum*PRODUCT_DEFECT_RATE;//次品数量
            totalNum-=tempNum;//合格品数量
            //合格品一经售出既有收入
            income+=totalNum*MARKET_SALE;

            //对于次品，if 拆解，重复步骤一、二
            if (dismantle == 1&&tempNum>0) {//当次品数量大于零时且确定是拆解策略时
                num1+=tempNum;
                num2+=tempNum;
                productCost+=calculateCost(part1Detect,part2Detect,productDetect,dismantle);//递归调用
            }
        } else {
            // 如果不检测，直接进入市场可能产生更大的损失
            productCost += PRODUCT_DEFECT_RATE * EXCHANGE_COST*totalNum;
            //对于退回的产品，重复步骤三
            double tempNum=totalNum*PRODUCT_DEFECT_RATE;//次品数量
            if (dismantle == 1&&tempNum>0) {//当次品数量大于零时且确定是拆解策略时
                num1+=tempNum;
                num2+=tempNum;
                productCost+=calculateCost(part1Detect,part2Detect,productDetect,dismantle);//递归调用
            }
        }

        // 返回总成本
        return part1Cost + part2Cost + productCost;
    }
}

