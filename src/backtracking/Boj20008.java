package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj20008 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, hp;
    static Skill[] skills;

    static int minimumTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        backtracking(0, hp, null);
        System.out.println(minimumTime);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        hp = Integer.parseInt(input[1]);

        skills = new Skill[n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int totalCoolDown = Integer.parseInt(input[0]);
            int damage = Integer.parseInt(input[1]);

            skills[i] = new Skill(totalCoolDown, 0, damage);
        }
    }

    public static void backtracking(int time, int remainHp, Skill prev) {
        for (int i = 0; i < time; i++) {
            System.out.print("\t");
        }
        System.out.println("TIME = " + time);
        for (int i = 0; i < time; i++) {
            System.out.print("\t");
        }
        System.out.println("HIT!! = " + prev);
        for (int i = 0; i < time; i++) {
            System.out.print("\t");
        }
        System.out.println("REMAIN HP = " + remainHp);
        for (int i = 0; i < time; i++) {
            System.out.print("\t");
        }
        System.out.println("SKILLS = " + Arrays.toString(skills));
        System.out.println("==========================");
        if (minimumTime <= time) {
            return;
        }
        if (remainHp <= 0) {
            minimumTime = Math.min(minimumTime, time);
            return;
        }

        //시간 경과
        for (Skill skill : skills) {
            skill.waits();
        }

        boolean isNeverHit = true;
        for (Skill skill : skills) {
            if (skill.isAvailable()) {
                isNeverHit = false;

                skill.coolDown = skill.totalCoolDown;
                backtracking(time + 1, remainHp - skill.damage, skill);
                skill.coolDown = 0;
            }
        }

        if (isNeverHit) {
            backtracking(time + 1, remainHp, null);
        }
    }

    static class Skill {

        int totalCoolDown; //총 대기시간
        int coolDown; //남은 대기시간
        int damage;

        public Skill(int totalCoolDown, int coolDown, int damage) {
            this.totalCoolDown = totalCoolDown;
            this.coolDown = coolDown;
            this.damage = damage;
        }

        public void waits() {
            if (coolDown > 0) {
                this.coolDown--;
            }
        }

        public void use() {
            this.coolDown = totalCoolDown;
        }

        public boolean isAvailable() {
            return coolDown <= 0;
        }

        @Override
        public String toString() {
            return "Skill{" +
                    "totalCoolDown=" + totalCoolDown +
                    ", coolDown=" + coolDown +
                    ", damage=" + damage +
                    '}';
        }
    }
}
