package edu.ufl.cise.cs1.robots;

import robocode.*;
import robocode.util.Utils;

import java.awt.*;
import java.awt.geom.Point2D;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Caffeine extends TeamRobot
{


        // Set colors

        //setAdjustGunForRobotTurn(true);
        //setAdjustRadarForRobotTurn(true);
        //fire divided by distance
        //These are constants. One advantage of these is that the logic in them (such as 20-3*BULLET_POWER)
        //does not use codespace, making them cheaper than putting the logic in the actual code.
        //Our bulletpower.
        //final static double BULLET_DAMAGE = BULLET_POWER * 4;//Formula for bullet damage.
        //Formula for bullet speed.

        //Variables
        static double dir = 1;
        static double enemyDistance;

        public void run () {
        //RamFire Colors
            setBodyColor(Color.blue);
            setGunColor(Color.blue);
            setRadarColor(Color.red);
            setScanColor(Color.white);
            setBulletColor(Color.blue);


        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setTurnRadarRight(361);
    }

        public void onScannedRobot (ScannedRobotEvent e){
            if(e.getName().contains("Caffeine")||e.getName().contains("VirtualBoy")||e.getName().contains("HimalayanYeti"))
            {
                isTeammate(e.getName());
                return;
            }


            double absBearing = e.getBearing() + getHeading();//absolute Bearing of enemy
            double BULLET_POWER = 3*Math.exp(-0.0018*(e.getDistance()));
            double BULLET_SPEED = 20 - 3 * BULLET_POWER;
            double turn=absBearing+21.12*Math.log(e.getDistance())-55;



            if(enemyDistance<(enemyDistance=e.getDistance())&&e.getDistance()>150)//50% without it
            {
                dir*=-1;
            }

        setTurnRight(Utils.normalRelativeAngleDegrees(turn - getHeading()));

        setAhead(110 * dir);


        setTurnGunRight(Utils.normalRelativeAngleDegrees(absBearing -
                getGunHeading() + (e.getVelocity() * Math.sin(e.getHeading() -
                absBearing) / BULLET_SPEED)));

        setFire(BULLET_POWER);

        setTurnRadarRight(Utils.normalRelativeAngleDegrees(absBearing - getRadarHeading()) *2.5);
    }

        public void onHitWall (HitWallEvent e){
        dir = -dir;
    }
    }



