package edu.ufl.cise.cs1.robots;

import robocode.*;
import robocode.Robot;
import sample.Target;

import java.awt.*;

public class HimalayanYeti extends TeamRobot
{
    public void run()
    {
        //turnLeft(getHeading());
        while(true)
        {
            //set colors
            setBodyColor(Color.blue);
            setGunColor(Color.blue);
            setRadarColor(Color.red);
            setBulletColor(Color.blue);
            setScanColor(Color.white);

            //int degreeToTurn = 360;

            ahead(50);
            turnRight(360);



            //configure the robot radar
            //used roboWiki.net/wiki/Radar
            //setAdjustGunForRobotTurn() and setAdjustRadarForGunTurn are called
            // to turn the gun and radar independently of the tank
            //setAdjustGunForRobotTurn(true);
            //setAdjustRadarForGunTurn(true);

        }
    }



    public void onScannedRobot(ScannedRobotEvent ev)
    {
        if(ev.getName().contains("HimalayanYeti") || ev.getName().contains("VirtualBoy"))
        {
            isTeammate(ev.getName());
            return;
        }


        else
        {
            turnRight(ev.getBearing());
            ahead(ev.getDistance() - 30);//try to ram into the scanned enemy robot
            setFire(3);

        }
    }

    public void onHitWall(HitWallEvent crash)
    {
        back(50);
        turnRight(360);

    }

    public void onHitByBullet(HitByBulletEvent what)
    {

        //if the robot shooting me is is in front then move back
        if(what.getBearing() < 90 && what.getBearing() > -90)
        {
            back(20);

        }

        //if the robot shooting me is in back then move front
        else
        {
            ahead(20);
        }


    }



}


/*
public class HimalayanYeti extends TeamRobot

{
    //determine to turn clockwise or anticlockwise
    int toTurn = 1;

    public void run()
    {


        //turnLeft(getHeading());
        while(true)
        {
            //set colors
            setBodyColor(Color.blue);
            setGunColor(Color.blue);
            setRadarColor(Color.red);
            setBulletColor(Color.yellow);//change this color to blue
            setScanColor(Color.white);



            //int degreeToTurn = 360;

            ahead(50);
            turnRight(30);

            turnRadarRight(toTurn * 360);



            //configure the robot radar
            //used roboWiki.net/wiki/Radar
            //setAdjustGunForRobotTurn() and setAdjustRadarForGunTurn are called
            // to turn the gun and radar independently of the tank
            setAdjustGunForRobotTurn(true);
            setAdjustRadarForGunTurn(true);

        }
    }



    public void onScannedRobot(ScannedRobotEvent ev)
    {
        if(ev.getName().contains("VirtualBoy") || ev.getName().contains("HimalayanYeti"))
        {
            isTeammate(ev.getName());
            return;
        }


        else
            {
                if(ev.getBearing() > 0)
                {
                    toTurn = 1;
                }

                else
                    {
                        toTurn = -1;
                    }
                    //turnRadarRight((getHeading() - getGunHeading() + ev.getBearing()) * toTurn);
                turnGunRight((getHeading() - getGunHeading() + ev.getBearing()) * toTurn);
                ahead(ev.getDistance() - 30);//try to ram into the scanned enemy robot
                setFire(3);

            }
    }

    public void onHitWall(HitWallEvent crash)
    {
        //backup from the wall and turnRight to scan the enemy
        back(50);
        turnRight(360);
        turnRadarRight(getHeading() - getGunHeading());


    }

    public void onHitByBullet(HitByBulletEvent what)
    {

        //if the robot shooting me is is in front then move back
        if(what.getBearing() < 90 && what.getBearing() > -90)
        {
            back(20);

        }

        //if the robot shooting me is in back then move front
        else
            {
                ahead(20);
            }


    }



}

*/

