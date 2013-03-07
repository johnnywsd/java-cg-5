
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class MyGraph extends BufferedApplet {
	private static final long serialVersionUID = -9176115728277601767L;
	private static final int scale = 200;  
	private int width = 850;
	private int height = 650;
	private int controlPanel_height = 140;
	private double alpha = 0;
	private double beta = 0;
	private double gamma = 0;
	private double moveStep = 10;
	private double moveX = 0;
	private double moveY = 0;
	private double moveZ = 0;
	int count=0; 
	private int jsScale = 100;
	private Dimension jsSize = new Dimension(100,30);

	private int geoType = -1;

	private JSListener myChangeLinster = new JSListener();
	private JSlider jsRotateX = new JSlider(-jsScale,jsScale);
	private JSlider jsRotateY = new JSlider(-jsScale,jsScale);
	private JSlider jsRotateZ = new JSlider(-jsScale,jsScale);


	private Button btnLeft = new Button("Move Left");
	private Button btnRight = new Button("Move Right");
	private Button btnUp = new Button("Move Up");
	private Button btnDown = new Button("Move Down");

	//	private Button btnAlphaAuto = new Button("X-axis");
	//	private Button btnBetaAuto = new Button("Y-axis");
	//	private Button btnGammaAuto = new Button("Z-axis");

	private Checkbox chkAlphaAuto = new Checkbox("X-axis");
	private Checkbox chkBetaAuto = new Checkbox("Y-axis");
	private Checkbox chkGammaAuto = new Checkbox("Z-axis");

	private Panel controlPanel = new Panel();
	private Panel subPanel1 = new Panel();
	private Panel subPanel2 = new Panel(); 
	private Panel subPanel3 = new Panel();
	private Panel subPanel4 = new Panel();

	private Panel controlPanel3 = new Panel();
	private Panel subPanel31 = new Panel();
	private JSlider jsR = new JSlider(0,100,100);
	private JSlider jsr = new JSlider(0,100,20);;
	private double R = 1;
	private double r = 0.2;

	private Panel subPanel32 = new Panel();
	private JSlider jsU = new JSlider(0,100,25);
	private JSlider jsV = new JSlider(0,100,25);;
	private double U = Math.PI/4;
	private double V = Math.PI/4;

	private int smooth = 25;
	private Panel subPanel33 = new Panel();
	private JSlider jsSmooth = new JSlider(0,50,25);


	private Panel subPanel34 = new Panel();
	private CheckboxGroup radioGroup = new CheckboxGroup();; 
	// The radio buttons to be selected 
	private Checkbox rdSolid = new Checkbox("Solid",radioGroup,false); 
	private Checkbox rdSkeleton = new Checkbox("Skeleton",radioGroup,true);


	private Panel subPanel35 = new Panel();
	private JSlider jsCenterMoveX = new JSlider(-100,100,0);
	private JSlider jsCenterMoveY = new JSlider(-100,100,0);
	private JSlider jsCenterMoveZ = new JSlider(-100,100,0);

	private Panel controlPanel2 = new Panel();

	private JSlider jsScaleX = new JSlider(-jsScale,jsScale,50);
	private JSlider jsScaleY = new JSlider(-jsScale,jsScale,50);
	private JSlider jsScaleZ = new JSlider(-jsScale,jsScale,50);
	private JSlider jsScaleXYZ = new JSlider(-jsScale,jsScale,50);

	private boolean alphaAutoFlag = false;
	private boolean betaAutoFlag = false;
	private boolean gammaAutoFlag = false;

	//	private double rotateStep = Math.PI/36;

	double startTime = System.currentTimeMillis() / 1000.0;

	int startX = 0;
	int startY =0;

	private Button btnCube = new Button("Cube");
	private Button btnOctahedron = new Button("Octahedron");
	private Button btnGlobe = new Button("Globe");
	private Button btnCylinder = new Button("Cylinder");
	private Button btnSphere = new Button("Sphere");
	private Button btnTorus = new Button("Torus");

	private double centerOffsetX = 0;
	private double centerOffsetY = 0;
	private double centerOffsetZ = 0;

	private JSlider jsMoveX = new JSlider(-100,100,0);
	private JSlider jsMoveY = new JSlider(-100,100,0);
	private JSlider jsMoveZ = new JSlider(-100,100,0);

	double scaleX = 1;
	double scaleY = 1;
	double scaleZ = 1;

	@Override
	public void init(){




		subPanel35.add(new Label("Center:"));
		subPanel35.setLayout(new FlowLayout(FlowLayout.LEFT));
		jsCenterMoveX.setBorder(BorderFactory.createTitledBorder("X"));
		jsCenterMoveY.setBorder(BorderFactory.createTitledBorder("Y"));
		jsCenterMoveZ.setBorder(BorderFactory.createTitledBorder("Z"));
		jsCenterMoveX.setPreferredSize(jsSize);
		jsCenterMoveY.setPreferredSize(jsSize);
		jsCenterMoveZ.setPreferredSize(jsSize);
		subPanel35.add(jsCenterMoveX);
		subPanel35.add(jsCenterMoveY);
		subPanel35.add(jsCenterMoveZ);

		jsCenterMoveX.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				centerOffsetX = jsCenterMoveX.getValue() / 100.0;
			}
		});
		jsCenterMoveY.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				centerOffsetY = jsCenterMoveY.getValue() / 100.0;
			}
		});
		jsCenterMoveZ.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				centerOffsetZ = jsCenterMoveZ.getValue() / 100.0;
			}
		});

		subPanel34.add(rdSkeleton);
		subPanel34.add(rdSolid);
		subPanel34.setLayout(new FlowLayout(FlowLayout.LEFT));


		jsSmooth.setBorder(BorderFactory.createTitledBorder("Smooth"));
		jsSmooth.setPreferredSize(jsSize);
		subPanel33.add(new Label("Smooth"));
		subPanel33.add(jsSmooth);
		subPanel33.setLayout(new FlowLayout(FlowLayout.LEFT));
		jsSmooth.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				smooth = jsSmooth.getValue();

			}
		});

		subPanel32.setLayout(new FlowLayout(FlowLayout.LEFT));
		subPanel32.add(new Label("Sphere:"));
		subPanel32.add(jsU);
		subPanel32.add(jsV);
		jsU.setBorder(BorderFactory.createTitledBorder("U"));
		jsV.setBorder(BorderFactory.createTitledBorder("V"));
		jsU.setPreferredSize(jsSize);
		jsV.setPreferredSize(jsSize);
		jsU.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				U = jsU.getValue() / 100.0 * Math.PI;
			}
		});
		jsV.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				V = jsV.getValue() / 100.0 * Math.PI;
			}
		});



		subPanel31.setLayout(new FlowLayout(FlowLayout.LEFT));
		subPanel31.add(new Label("Torus:"));
		subPanel31.add(jsR);
		subPanel31.add(jsr);
		jsR.setBorder(BorderFactory.createTitledBorder("R"));
		jsr.setBorder(BorderFactory.createTitledBorder("r"));
		jsR.setPreferredSize(jsSize);
		jsr.setPreferredSize(jsSize);
		jsR.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				R = jsR.getValue() / 100.0;
			}
		});
		jsr.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				r = jsr.getValue() / 100.0;
			}
		});

		controlPanel3.setLayout(new GridLayout(5,1));
		controlPanel3.add(subPanel34);
		controlPanel3.add(subPanel33);
		controlPanel3.add(subPanel32);
		controlPanel3.add(subPanel31);
		controlPanel3.add(subPanel35);
		controlPanel3.setBounds(460, 390, width-460, 200);
		controlPanel3.setBackground(Color.WHITE);
		this.add(controlPanel3);

		controlPanel2.setLayout(new GridLayout(6,1));
		controlPanel2.add(btnCube);
		controlPanel2.add(btnOctahedron);
		controlPanel2.add(btnGlobe);
		controlPanel2.add(btnCylinder);
		controlPanel2.add(btnSphere);
		controlPanel2.add(btnTorus);
		controlPanel2.setBackground(Color.WHITE);

		ChangeGeometryListener cgl = new ChangeGeometryListener();
		btnCube.addActionListener(cgl);
		btnOctahedron.addActionListener(cgl);
		btnGlobe.addActionListener(cgl);
		btnCylinder.addActionListener(cgl);
		btnSphere.addActionListener(cgl);
		btnTorus.addActionListener(cgl);

		controlPanel2.setBounds(460, 150, 100, 200);
		this.add(controlPanel2);


		controlPanel.setLayout(new GridLayout(4,1));
		//		controlPanel.add(subPanel31);
		controlPanel.add(subPanel3);
		controlPanel.add(subPanel1);
		controlPanel.add(subPanel2);
		controlPanel.add(subPanel4);
		subPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		subPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		subPanel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		subPanel4.setLayout(new FlowLayout(FlowLayout.LEFT));

		subPanel1.add(new Label("Rotate:"));

		jsRotateX.setBorder(BorderFactory.createTitledBorder("X"));
		jsRotateY.setBorder(BorderFactory.createTitledBorder("Y"));
		jsRotateZ.setBorder(BorderFactory.createTitledBorder("Z"));
		jsRotateX.setPreferredSize(jsSize);
		jsRotateY.setPreferredSize(jsSize);
		jsRotateZ.setPreferredSize(jsSize);

		subPanel1.add(jsRotateX);
		subPanel1.add(jsRotateY);
		subPanel1.add(jsRotateZ);
		jsRotateX.addChangeListener(myChangeLinster);
		jsRotateY.addChangeListener(myChangeLinster);
		jsRotateZ.addChangeListener(myChangeLinster);

		//		subPanel1.add(btnAlpha);
		//		subPanel1.add(btnBeta);
		//		subPanel1.add(btnGamma);

		subPanel2.add(new Label("Translate:"));
		subPanel2.add(jsMoveX);
		subPanel2.add(jsMoveY);
		subPanel2.add(jsMoveZ);
		jsMoveX.setBorder(BorderFactory.createTitledBorder("X"));
		jsMoveY.setBorder(BorderFactory.createTitledBorder("Y"));
		jsMoveZ.setBorder(BorderFactory.createTitledBorder("Z"));
		jsMoveX.setPreferredSize(jsSize);
		jsMoveY.setPreferredSize(jsSize);
		jsMoveZ.setPreferredSize(jsSize);
		jsMoveX.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				moveX = jsMoveX.getValue();
			}
		});
		jsMoveY.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				moveY = jsMoveY.getValue();
			}
		});
		jsMoveZ.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				moveZ = jsMoveZ.getValue();
			}
		});



		subPanel3.add(new Label("Rotate Automatically:"));
		//		subPanel3.add(btnAlphaAuto);
		//		subPanel3.add(btnBetaAuto);
		//		subPanel3.add(btnGammaAuto);
		subPanel3.add(chkAlphaAuto);
		subPanel3.add(chkBetaAuto);
		subPanel3.add(chkGammaAuto);


		subPanel4.add(new Label("Scale"));
		subPanel4.add(jsScaleX);
		subPanel4.add(jsScaleY);
		subPanel4.add(jsScaleZ);
		subPanel4.add(jsScaleXYZ);
		jsScaleX.setBorder(BorderFactory.createTitledBorder("X"));
		jsScaleY.setBorder(BorderFactory.createTitledBorder("Y"));
		jsScaleZ.setBorder(BorderFactory.createTitledBorder("Z"));
		jsScaleX.setPreferredSize(jsSize);
		jsScaleY.setPreferredSize(jsSize);
		jsScaleZ.setPreferredSize(jsSize);
		jsScaleXYZ.setPreferredSize(jsSize);
		jsScaleXYZ.setBorder(BorderFactory.createTitledBorder("XYZ"));


		jsScaleX.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				scaleX = (double)jsScaleX.getValue()/jsScale *2;
			}
		});
		jsScaleY.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				scaleY = (double)jsScaleY.getValue()/jsScale *2;
			}
		});
		jsScaleZ.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				scaleZ = (double)jsScaleZ.getValue()/jsScale *2;
			}
		});
		jsScaleXYZ.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int value = jsScaleXYZ.getValue();
				jsScaleX.setValue(value);
				jsScaleY.setValue(value);
				jsScaleZ.setValue(value);
			}
		});



		controlPanel.setSize(width, height);
		controlPanel.setBackground(Color.WHITE);

		MoveListener ml = new MoveListener();
		btnLeft.addActionListener(ml);
		btnRight.addActionListener(ml);
		btnUp.addActionListener(ml);
		btnDown.addActionListener(ml);

		this.setLayout(null);
		controlPanel.setBounds(0, height-controlPanel_height-10, width-100, controlPanel_height);
		//		controlPanel.setBounds(500, 50, width-500, controlPanel_height);
		this.add(controlPanel);

		geo = Geometry.CubeFactory();

		this.setSize(width,height);

	}

	private class JSListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			// TODO Auto-generated method stub
			JSlider source = (JSlider)arg0.getSource();
			if(source == jsRotateX){
				alpha =(double)source.getValue()/jsScale * Math.PI;
			}else if(source == jsRotateY){
				beta =(double)source.getValue()/jsScale * Math.PI;
			}else if(source == jsRotateZ){
				gamma =(double)source.getValue()/jsScale * Math.PI;
			}
		}

	}


	public class ChangeGeometryListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Button btn = (Button)e.getSource();
			if(btn == btnCube)
				geoType = Geometry.CUBE;
			else if(btn == btnOctahedron){
				geoType = Geometry.OCTAHEDRON;
			}else if(btn == btnGlobe){
				geoType = Geometry.GLOBE;
			}else if(btn == btnSphere){
				geoType = Geometry.SPHERE;
			}else if(btn == btnCylinder){
				geoType = Geometry.CYLINDER;
			}else if(btn == btnTorus){
				geoType = Geometry.TORUS;
			}

		}

	}

	public class MoveListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == btnLeft){
				moveX -=moveStep;
			}else if(arg0.getSource() == btnRight){
				moveX +=moveStep;
			}else if(arg0.getSource() == btnUp){
				moveY -=moveStep;
			}else if(arg0.getSource() == btnDown){
				moveY +=moveStep;
			}
		}

	}


	public boolean mouseDown(Event e, int x, int y) {
		startX = x;
		startY = y;

		return true;
	}

	int currentX = 0;
	int currentY = 0;
	public boolean mouseDrag(Event e, int x, int y) {
		currentX = x;
		currentY = y;
		alpha = ((double)(-currentY+startY))/100;
		beta = ((double)(-currentX+startX))/100;
		//		gamma = ((double)(-currentY+startY))/60;
		return true;
	}



	public boolean mouseUp(Event e, int x, int y){
		//		this.verticesM = this.result;
		//		System.out.println("UP");
		return true;
	}



	private Geometry geo = null; 
	@Override
	public void render(Graphics g) {

		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier", Font.ITALIC, 50));
		g.drawString("You can drag!", 10, 70);
		g.setFont(new Font("Courier", Font.ITALIC, 18));
		g.drawString("Or click the buttons below", 130, 100);




		double time = System.currentTimeMillis() / 1000.0 - startTime;
		double rate = 5; 

		if(alphaAutoFlag)
			alpha = time/rate;
		if(betaAutoFlag)
			beta = time/rate;
		if(gammaAutoFlag)
			gamma = time/rate;

		if(chkAlphaAuto.getState())
			alpha = time/rate;
		if(chkBetaAuto.getState())
			beta = time/rate;
		if(chkGammaAuto.getState())
			gamma = time/rate;



		//		double scaleX = (double)jsScaleX.getValue()/jsScale *2;
		//		double scaleY = (double)jsScaleY.getValue()/jsScale *2;
		//		double scaleZ = (double)jsScaleZ.getValue()/jsScale *2;

		//		geo = Geometry.GLobeFactory(25);


		if(geoType == Geometry.CUBE)
			geo = Geometry.CubeFactory();
		else if(geoType == Geometry.OCTAHEDRON){
			geo = Geometry.OctahedronFactory();
		}else if(geoType == Geometry.GLOBE){
			geo = Geometry.GLobeFactory(smooth);
		}else if(geoType == Geometry.SPHERE){
			geo = Geometry.SphereFactory(smooth, U, V);
		}else if(geoType == Geometry.CYLINDER){
			geo = Geometry.CylinderFactory(smooth);
		}else if(geoType == Geometry.TORUS){
			geo = Geometry.TorusFactory(smooth, R, r);
		}



		geo.matrix.identity();

		if(geo.getType() == Geometry.CUBE)
			geo.matrix.setCenter(0.5+centerOffsetX, 0.5+centerOffsetY, 0.5+centerOffsetZ);
		else
			geo.matrix.setCenter(centerOffsetX, centerOffsetY, centerOffsetZ);

		geo.matrix.scale(scale);
		//		geo.matrix.setCenter(scale/2, scale/2,scale/2 );
		//		transM.translate(-scale/2,-scale/2,-scale/2);
		geo.matrix.scale(scaleX,scaleY,scaleZ);
//		if(geo.getType() == Geometry.CUBE)
//			geo.matrix.setCenter(scale/2+centerOffsetX, scale/2+centerOffsetY, scale/2+centerOffsetZ);
//		else
//			geo.matrix.setCenter(centerOffsetX, centerOffsetY, centerOffsetZ);

		geo.matrix.rotateZ(gamma);
		geo.matrix.rotateY(beta);
		geo.matrix.rotateX(alpha);

		geo.setPosition(200+(int)moveX, 250+(int)moveY, (int)moveZ);


		geo.drawCenter(g);

		if(rdSkeleton.getState())
			geo.drawSkeleton(g, true);
		else
			geo.drawFill(g, true, false);


		g.setColor(Color.BLACK);


	}


}
