package javaapplication2;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Graficas extends JPanel
{   
    public Color color_perimetro;//color de perimetro
    public Color color_fondo;//color de fondo

    public int sleep = 20;
    public int tam_pixel = 2;
    
    private int punto_x;
    private int punto_y;
    
    private final JFrame frame = new JFrame("graficas");
    
    public Graficas()
    {
       init_frame();
       color_perimetro = Color.BLACK;
       color_fondo = Color.BLACK;
       punto_x = punto_y = 0;
    }

    private void init_frame()
    {
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color_perimetro);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillOval(punto_x,punto_y,tam_pixel,tam_pixel);
    }
    
    public void posicionar(int aux1, int aux2)
    {
        this.punto_x = aux1;
        this.punto_y = aux2;
    }
    
    public void dibujarRecta(int x1, int y1, int x0, int y0)
    {
        try {
            dibujarRecta_p(x1,y1,x0,y0);
        } catch (InterruptedException ex) {
           System.out.println(ex);
        }
    }

    private void dibujarRecta_p(int x0, int y0, int x1, int y1)throws InterruptedException
    {   
        int var = x1<x0 ? -1:1;
        float m = (((float)y1-y0)/((float)x1-x0))*var;
        float aux = y0;
        int x2 = x0;
        int y2 = y0;
        if(x2 == x1){
            while(y2 != y1){
                int var_y = y1<=y0 ? -1:1;
                posicionar(x2,y2);
                y2 = y2+var_y;
                repaint();
                Thread.sleep(sleep);}
        }
        else{
            while(x2!=x1){
                posicionar(x2,y2);
                x2 = x2+var;
                aux = aux+m;
                y2 = (int) aux;
                repaint();
                Thread.sleep(sleep);}
        }     
    }
    //el dato de la posicion [i] de x pertenece al mismo punto de [i]y
    public void dibujarPoligono(int [] x, int [] y)
    {
        int tam = x.length;
        for(int i = 0; i<tam; i++)
        {
            if(tam-1 == i){
                dibujarRecta(x[i],y[i],x[0],y[0]);
            }
            else{
                dibujarRecta(x[i],y[i],x[i+1],y[i+1]);
            }
        }            
    }

    public static void main(String[] args)
    {
        Graficas gra = new Graficas();
        gra.dibujarPoligono(new int[]{100,200,300},new int[]{100,100,300});
    }
}
