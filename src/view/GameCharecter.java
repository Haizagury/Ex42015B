package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class GameCharecter {

		   int x,y;
		   String filename = new String("lib\\bambaBaby.jpg");
		  // String filename = new String("lib\\star.gif");
		   String fileName1 = new String("lib\\images.pnp");
		   
		   public GameCharecter(int x,int y) {
			this.x=x;this.y=y;
		   }
		   
		   public void paint(PaintEvent e,int w,int h){
			   
				Image image = new Image(new Device() {
					@Override
	
					public int internal_new_GC(GCData arg0) {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public void internal_dispose_GC(int arg0, GCData arg1) {
						// TODO Auto-generated method stub
						
					}
				}, filename);  
				
				ImageData imageData =  image.getImageData();
				e.gc.drawImage(image, 0, 0, imageData.width , imageData.height -10, x-10, y, w-10, h-10);
		   }
		   
		   public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public void paint(PaintEvent e,int w,int h, int x, int y){
			   
				Image image = new Image(new Device() {
					@Override
	
					public int internal_new_GC(GCData arg0) {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public void internal_dispose_GC(int arg0, GCData arg1) {
						// TODO Auto-generated method stub
						
					}
				}, filename);  
				
				ImageData imageData =  image.getImageData();
				e.gc.drawImage(image, 0, 0, imageData.width - 10, imageData.height - 10, x, y, w, h);
		   }
		

}
