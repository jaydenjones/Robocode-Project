package edu.ufl.cise.cs1.robots;

import robocode.*;
import robocode.util.Utils;

import java.awt.*;

public class Caffeine extends TeamRobot
{

        static double dir = 1;
        static double enemyDistance;//allows to check distance from previous round

        public void run () {
            setBodyColor(Color.blue);
            setGunColor(Color.blue);
            setRadarColor(Color.red);
            setScanColor(Color.white);
            setBulletColor(Color.blue);


        setAdjustGunForRobotTurn(true);//allows gun to turn separately from body
        setAdjustRadarForGunTurn(true);//allows radar to turn separately from body
        setTurnRadarRight(361);//keeps spinning radar if nothing found
    }

        public void onScannedRobot (ScannedRobotEvent e){
            //makes sure team bots don't get hit
            if(e.getName().contains("Caffeine")||e.getName().contains("VirtualBoy")||e.getName().contains("HimalayanYeti"))
            {
                isTeammate(e.getName());
                return;
            }


            double absBearing = e.getBearing() + getHeading();//absolute Bearing of enemy
            double power = 3*Math.exp(-0.0018*(e.getDistance()));//created an excel trendplot to a map an exponential increase curve
            double speed = 20 - 3 * power;
            double turn=absBearing+21.12*Math.log(e.getDistance())-60;//created a function for a logarithmic turn that causes the bot to spiral inward

            if(enemyDistance<(enemyDistance=e.getDistance())&&e.getDistance()>175)//creates more of a randomness further away to dodge bullets
            {
                dir*=-1;
            }

        setTurnRight(Utils.normalRelativeAngleDegrees(turn - getHeading()));

        setAhead(100 * dir);

        //linear targeting system//cite robowiki linear targeting
        double adjustAng=absBearing-getGunHeading();//the angle where the enemy is
        double linearAprox;
        double parallelAng=e.getHeading()-absBearing;//the lateral angle of the enemy robot
        double yVelocity= e.getVelocity()*Math.sin(parallelAng);//the lateral velocity of the robot as it moves parallel
        linearAprox=Math.asin(yVelocity/speed) * (Math.random()*-0.2+1.0);//the linear approximation angle of where the robot might be
        //randomness added to account for fluctuations in acceleration of the enemy
        setTurnGunRight(Utils.normalRelativeAngleDegrees(adjustAng+linearAprox));//approximately where the enemy should be
        setFire(power);

        setTurnRadarRight(Utils.normalRelativeAngleDegrees(absBearing - getRadarHeading()) *2.5);//wide scope radar
    }
        public void onHitWall (HitWallEvent e){//switches direction if a wall is hit
        dir = -dir;
    }
    }



