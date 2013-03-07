import java.awt.Color;
import java.awt.Graphics;


public class Stage extends BufferedApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7321989914726043131L;
	private static final int width = 500;
	private static final int height = 500;

	Geometry cube;
	Geometry child; 
	Geometry head;
	Geometry body;
	Geometry leftArm;
	Geometry rightArm;
	Geometry leftLeg;
	Geometry rightLeg;
	@Override
	public void init(){
		this.setSize(width,height);
//		cube = Geometry.GeometryFactory(Geometry.CUBE);
//		cube.setPosition(200, 200, 200);
//		
//		child = Geometry.GeometryFactory(Geometry.CUBE);
//		child.setPosition(100, 100, 100);
//		cube.matrix.scale(200);
//		cube.add(child);
		
//		head = Geometry.GeometryFactory(Geometry.GLOBE);
//		head = Geometry.GLobeFactory(30);
//		body = Geometry.GLobeFactory(30);
//		
		head = Geometry.CubeFactory();
		body = Geometry.CubeFactory();
		leftArm = Geometry.CubeFactory();
		rightArm = Geometry.CubeFactory();
		leftLeg = Geometry.CubeFactory();
		rightLeg = Geometry.CubeFactory();
		body.add(leftArm);
		body.add(rightArm);
		body.add(leftLeg);
		body.add(rightLeg);
		body.add(head);
		
	}
	double time;
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		
		time = Math.sin(System.currentTimeMillis()/1000.0)*0.4 ;
		body.identity();
		head.identity();
		leftArm.identity();
		rightArm.identity();
		
		leftArm.matrix.scale(100,30,30);
		rightArm.matrix.scale(100,30,30);
		
		leftLeg.matrix.scale(100,30,30);
		rightLeg.matrix.scale(100,30,30);
		leftLeg.matrix.setCenter(50, 0, 0);
		rightLeg.matrix.setCenter(-50, 0, 0);
		
		
		head.matrix.scale(100);
		body.matrix.scale(50,200,50);
		
//		head.matrix.setCenter(50, 50, 50);
		leftArm.matrix.setCenter(50, 0, 0);
		rightArm.matrix.setCenter(-50, 0, 0);
		
		
//		body.matrix.scale(100);
		leftArm.setPosition(220, 200, 0);
		rightArm.setPosition(280, 200, 0);
		
		leftLeg.setPosition(220, 360, 0);
		rightLeg.setPosition(280, 360, 0);
		
		leftLeg.matrix.rotateZ(-Math.PI/3);
		rightLeg.matrix.rotateZ(Math.PI/3);
		
		
		head.matrix.setCenter(0, 50, 0);
		
		
//		body.setPosition(200, 200, 0);
//		head.setPosition(220, 130, -25);
		head.setPosition(250, 140, 00);
		body.setPosition(250, 250, 00);
		
		
		leftArm.matrix.rotateZ(time);
		rightArm.matrix.rotateZ(-time);
//		body.rotateY(time*4);
//		head.matrix.rotateY(time*4);
//		body.matrix.rotateY(time*4);


//		body.drawSkeletonAll(g, true);
		body.drawCenter(g);
//		body.matrix.rotateZ(time);
		
//		head.matrix.rotateZ(time);
		
//		head.drawSkeleton(g, true);
		
		
		body.rotateY(time);
		
//		body.matrix.rotateX(Math.PI/2);
		head.matrix.rotateZ(time);
		leftLeg.matrix.rotateX(time);
		rightLeg.matrix.rotateX(-time);
		
//		body.rotateX(time/3);
//		body.rotateX(Math.PI/2);
		
		body.drawSkeletonAll(g, true);
		
		
//		cube.identity();
//		child.matrix.scale(0.50);
//		cube.scale(200);
//		cube.rotateX(time);
//		cube.rotateY(time);
//		cube.rotateZ(time);
//		cube.drawSkeletonAll(g, true);
////		cube.drawFillAll(g, true, false);
//		System.out.println(cube.matrix.getCenterX());
//		System.out.println(cube.getPositionX());
//		System.out.println(child.matrix.getCenterX() +"C");
//		System.out.println(child.getPositionX() +"C");
//		System.out.println(cube.matrix.getCenterX());
//		System.out.println(child.matrix.getCenterX()+"C");
		
		
		
//		cube = Geometry.GeometryFactory(Geometry.CUBE);
//		cube.matrix.scale(200);
//		cube.setPosition(100, 100, 100);
////		cube.matrix.setCenter(200, 200, 200);
////		cube.matrix.setCenter(-50, -50, -50);
//		cube.matrix.setCenter(100, 100, 100);
////		cube.matrix.setCenter(0, 0, 0);
//		cube.drawCenter(g);
//		cube.matrix.rotateX(time);
//		cube.matrix.rotateY(time);
//		cube.matrix.rotateZ(time);
////		cube.setPosition(200, 200, 200);
//		cube.drawSkeleton(g, true);
		
		

	}

}
