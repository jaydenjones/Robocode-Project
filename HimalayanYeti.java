package edu.ufl.cise.cs1.robots;

import robocode.*;
import robocode.Robot;
import sample.Target;

import java.awt.*;

public class HimalayanYeti extends TeamRobot
{
    public void run()
    {

        while(true)
        {
            //set colors
            setBodyColor(Color.blue);
            setGunColor(Color.blue);
            setRadarColor(Color.red);
            setBulletColor(Color.blue);
            setScanColor(Color.white);


            //movement of the robot
            turnRight(360);

        }
    }



    public void onScannedRobot(ScannedRobotEvent ev)
    {
        if(ev.getName().contains("HimalayanYeti") || ev.getName().contains("VirtualBoy") || ev.getName().contains("Caffeine"))
        {
            isTeammate(ev.getName());
            return;
        }


        else
        {
            //turn toward the enemy
            turnRight(ev.getBearing());



            //shoot the enemy with maximum power for maximum damage according to the distance
            if(ev.getDistance() > 200)
            {
                setFire(2.5);
            }

            else if(ev.getDistance() > 0)
            {
                setFire(3);
            }

            //move ahead 50 pixels and ram the enemy
            setAhead(50);

        }
    }

    public void onHitWall(HitWallEvent crash)
    {
        //if the wall is at the front of the robot
        if(crash.getBearing() < 90 && crash.getBearing() > -90)
        {
            //go back and then scan
            back(50);
            turnRight(360);
        }

        //if the wall is to the back of the robot
        else
            {
                //move forward and scan
                ahead(50);
                turnRight(360);
            }


    }

    public void onHitByBullet(HitByBulletEvent what)
    {

        //if the robot shooting me is is in the front
        if(what.getBearing() < 90 && what.getBearing() > -90)
        {
            //turn left and move ahead
            turnRight(-20);
            ahead(30);
        }

        //if the robot shooting me is in the back
        else
        {
            //turn right and move ahead
            turnRight(20);
            ahead(20);
        }
    }

}



