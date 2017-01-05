import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class SalesmanPersonal extends PApplet{
	
	ArrayList<PVector> points = new ArrayList<PVector>();	//Array of points which represent our cities
	PVector currentPoint = new PVector(0,0);				//Current point/city we are located at
	int numPoints = 1000000;									//Number of points for this example
	float totalDist = 0;									//Total distance the salesman has traveled
	
	//MAIN
	public static void main(String[] args){
		
		PApplet.main("SalesmanPersonal");
	}
	
	//SETTINGS
	public void settings(){
		
		size(800,800);
    }
	
	//SETUP
    public void setup(){
    	
    	background(0);
    	frameRate(999999999);
    	
    	
    	//Create random points between the width and height of the window
    	for(int i = 0; i < numPoints; i++){
    		
	    	float rx = random(40,width-40);
	    	float ry = random(40,height-40);
	    	points.add(new PVector(rx,ry));
    	}
    	
    	//Draw the points to the window
    	stroke(255,0,0);
    	fill(255,0,0);
    	ellipse(points.get(0).x, points.get(0).y,15,15);
    	for(int i = 1; i < points.size(); i++){
    		
    		stroke(100,100,100);
        	fill(100,100,100);
    		ellipse(points.get(i).x, points.get(i).y,8,8);	
    	}
    	
    	//Our current point will start with the first point in our array
    	currentPoint = points.get(0);
    }
    
    //DRAW
    public void draw(){
    	
    	PVector nextPoint = new PVector(0,0);	//nextPoint will be the closest point to the current point
    
    	float shortestDist = 999999999;
    	
    	//Find the shortest point to jump to and go to it
    	for(int i = 0; i < points.size(); i++){
    		
    		float distance = dist(currentPoint.x, currentPoint.y, points.get(i).x, points.get(i).y);
    		if(distance < shortestDist && distance != 0){
    			shortestDist = distance;
    			nextPoint.x = points.get(i).x;
    			nextPoint.y = points.get(i).y;
    			
    		}
    	}
    	totalDist += shortestDist;
    	
    	//Make the point we jump to green
    	stroke(0,117,0);
    	fill(0,117,0);
    	ellipse(nextPoint.x, nextPoint.y,8,8);
    	
    	//Draw a line between the current point and the point we just jumped to
    	stroke(255, 255, 255);
    	strokeWeight(3);
    	line(currentPoint.x, currentPoint.y, nextPoint.x, nextPoint.y);
    	
    	//Remove the current point from the array
    	points.remove(currentPoint);
    	
    	//Make our new current point the one we jumped to (nextPoint)
    	currentPoint = nextPoint;
    	
    	//Code to draw the distance counter in the upper left
    	fill(0);
    	stroke(0);
    	rect(0,0,500,34);
    	fill(255);
    	stroke(255);
    	textSize(20);
    	text("Distance:" + totalDist, 0,20);
    	
    	//End the animation when we're at the last point
    	if(points.size() == 1)
    		noLoop();
    	  	
    }
}
