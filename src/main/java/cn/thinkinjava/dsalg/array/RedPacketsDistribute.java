package cn.thinkinjava.dsalg.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 经典红包分配算法
 * 红包的生成有2种方式：一种是预算（占用存储，增加IO），一种是实时计算
 *
 * 要求：
 * 1、所有人抢到的红包之和等于总金额
 * 2、参与抢红包的人，抢到时最少是0.01元
 *
 * 参考：
 * <a href="https://developer.aliyun.com/article/1166688">二倍均值随机算法之抢拼手气红包场景应用</a>
 * <a href="https://cloud.kepuchina.cn/newSearch/imgText?id=6887717254358626304">破解红包算法！为啥“手气最佳”总是别人?</a>
 * <a href="https://www.zybuluo.com/yulin718/note/93148">微信红包的架构设计简介 </a>
 *
 * @author qiuxianbao
 * @date 2025/02/17
 */
public class RedPacketsDistribute {

    public static void main(String[] args) {
        int totalAmount = 100 * 100;
        int totalNum = 10;

        List<Integer> redPackets0 = distributeRedPackets0(totalAmount, totalNum);
        System.out.println(redPackets0);
        System.out.println(redPackets0.stream().mapToInt(Integer::intValue).sum() == totalAmount);

        List<Integer> redPackets = distributeRedPackets(totalAmount, totalNum);
        System.out.println(redPackets);
        System.out.println(redPackets.stream().mapToInt(Integer::intValue).sum() == totalAmount);
    }

    /**
     * 线段切割法
     *
     * 思路：
     * 将总金额看作一条线段，随机在该线段上切割，
     * 切割点的数量等于红包数量减1，每个切割点之间的线段长度即为一个红包的金额
     *
     * @param totalAmount 总金额，单位分
     * @param totalNum  总人数
     * @return
     */
    private static List<Integer> distributeRedPackets(int totalAmount, int totalNum) {
        List<Integer> redPackets = new ArrayList<>();
        Random random = new Random();
        // 生成切割点
        List<Integer> cutPoints = new ArrayList<>();
        for (int i = 0; i < totalNum - 1; i++) {
            // 生成 [1, totalAmount-1]
            int cutPoint = 1 + random.nextInt(totalAmount - 1);
            cutPoints.add(cutPoint);
        }

        Collections.sort(cutPoints);

        int lastCutPoint = 0;
        for (int cutPoint : cutPoints) {
            redPackets.add(cutPoint - lastCutPoint);
            lastCutPoint = cutPoint;
        }

        // 最后一个红包的金额为最后一个切割位置与总金额之间的差值
        redPackets.add(totalAmount - lastCutPoint);
        return redPackets;
    }


    /**
     * 雨露均沾，二倍均值法
     *
     * 除了最后一个人，其他人最多允许抽到剩余平均值的2倍，目的是为了把每个人可能抽到的最高金额强行降低
     * 缺点：最后一个人分到的钱可能很大
     *
     * 思路：
     * 每次随机分配时，从剩余金额中随机取一个值，这个值是剩余金额的2倍除以剩余红包数再减去一个很小的值，
     * 以确保每次分配的金额不会过大或过小
     *
     * @param totalAmount 总金额，单位分
     * @param totalNum  总人数
     * @return
     */
    private static List<Integer> distributeRedPackets0(int totalAmount, int totalNum) {
        List<Integer> redPackets = new ArrayList<>();
        Random random = new Random();
        // 剩余金额, 剩余人数
        int restAmount = totalAmount;
        int restNum = totalNum;

        for (int i = 0; i < totalNum - 1; i++) {
            // 单个红包不低于1分
            int min = 1;
            int max = restAmount / restNum * 2 - 1;
            if (max < min) {
                max = min;
            }

            // 生成一个 [min, max] 之间的随机数
            int randomAmount = min + random.nextInt(max - min + 1);
            redPackets.add(randomAmount);
            restAmount -= randomAmount;
            restNum--;
        }

        // 最后一个红包直接分配剩余的金额
        redPackets.add(restAmount);
        return redPackets;
    }

}
