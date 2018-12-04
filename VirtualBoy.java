package edu.ufl.cise.cs1.robots;

import robocode.*;
import robocode.util.Utils;


import java.awt.*;



public class VirtualBoy extends TeamRobot
{

    public void run() {


    setBodyColor(new Color(4, 57,164));
    setGunColor(new Color(4, 57,164));
    setRadarColor(new Color(255, 51,0));
    setBulletColor(new Color(0, 0,0));
    setScanColor(new Color(255, 51,0));
    setAdjustRadarForRobotTurn(true);
    setAdjustGunForRobotTurn(true);



        while(true)
    {
        setAhead(100);
        setTurnRadarRight(360);
        execute();

    }

}

    public void onHitWall(HitWallEvent event){

        setBack(100);
        setTurnLeft(90);
        execute();

    }

    public void onScannedRobot(ScannedRobotEvent event) {

        setTurnRadarRight(getHeading() - getRadarHeading() + event.getBearing()); //cite Fire robot/Tracker
        setTurnGunRight(getHeading() - getGunHeading() + event.getBearing());   //cite Fire robot/Tracker
        double firePower = ( 500 / event.getDistance());        //http://robowiki.net/wiki/Selecting_Fire_Power
        double distance = (event.getDistance()/ (20 - firePower * 3));
        double timeToTravel = event.getDistance() / firePower;
       /* double absoluteBearing = getHeadingRadians() + event.getBearingRadians(); //http://robowiki.net/wiki/Lateral_velocity
        double lateralVelocity = event.getVelocity() * Math.sin(event.getHeadingRadians() - absoluteBearing);  //Velocity * sin(theta); basic projectile physics something like that // cite http://robowiki.net/wiki/Lateral_velocity
        double linearBearing = absoluteBearing + Math.asin(event.getVelocity() / lateralVelocity); http://robowiki.net/wiki/Linear_Targeting
        setTurnGunRightRadians(getRadarHeadingRadians()); //cite TrackFire Robot */
        setFire(firePower);
        double headOnBearing = getHeadingRadians() + event.getBearingRadians();
        double linearBearing = headOnBearing + Math.asin(event.getVelocity() / Rules.getBulletSpeed(firePower) * Math.sin(event.getHeadingRadians() - headOnBearing));
        setTurnGunRightRadians(Utils.normalRelativeAngle(linearBearing - getGunHeadingRadians()));
        if (event.getName().contains("PassBot")){

            setFire(firePower);
            execute();
        }
        if (event.getName().contains("FailBot")){

            setFire(firePower);
            execute();
        }
        if (event.getName().contains("Megazord")) {

            isTeammate(event.getName());
            return;
        }
        if (isTeammate((event.getName()))){

            return;
        }

        scan();




    }

    public void onHitByBullet(HitByBulletEvent hitByBulletEvent){



    }



    public void onHitRobot(HitRobotEvent e) {

    }




        }

