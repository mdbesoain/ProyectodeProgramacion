/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.*;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import proyectoprogra.CommandNames;

/**
 *
 * @author pablo
 */
public class Line extends Element
{

    private ArrayList<Point> points;
    private String thin;
    private String optionsLine;
    private String styleLine;
    private Color color;
    private boolean linea;
    private Point pointInicio;
    private Point pointFinal;
    private boolean brush;
    private String conector;

    /**
     * Constructor
     */
    public Line ()
    {
        this.points = new ArrayList<> ();
        this.optionsLine = CommandNames.LINE_NORMAL;
        this.styleLine = CommandNames.ICON_Linea;
        this.conector = CommandNames.ICON_LineaNormal;
        this.thin = CommandNames.GROSOR_1PX;
        this.color = Color.BLACK;
        this.linea = false;
        this.brush = false;
    }

    public Line ( boolean b )
    {
        this.points = new ArrayList<> ();
        this.thin = CommandNames.GROSOR_1PX;
        this.optionsLine = CommandNames.LINE_NORMAL;
        this.styleLine = CommandNames.ICON_Linea;
        this.conector = CommandNames.ICON_LineaNormal;
        this.color = Color.BLACK;
        this.linea = b;
        this.brush = false;
    }

    public Line ( boolean b , String optionsLine , String styleLine, String conector )
    {
        this.points = new ArrayList<> ();
        this.styleLine = styleLine;
        this.thin = CommandNames.GROSOR_1PX;
        this.optionsLine = optionsLine;
        this.conector = conector;
        this.color = Color.BLACK;
        this.linea = b;
        this.brush = false;
    }

    public void setColor ( Color color )
    {
        this.color = color;
    }

    public void setThin ( String thin )
    {
        this.thin = thin;
    }

    public boolean add ( Point e )
    {
        return points.add ( e );
    }

    public void setPointFinal ( Point pointFinal )
    {
        this.pointFinal = pointFinal;
    }

    public void setPointInicio ( Point pointInicio )
    {
        this.pointInicio = pointInicio;
    }

    public boolean isEmpty ()
    {
        return points.isEmpty ();
    }

    public void setBrush ( boolean brush )
    {
        this.brush = brush;
    }

    private BasicStroke CreateGrosor ()
    {

        switch ( this.thin )
        {
            case CommandNames.GROSOR_1PX:
                return new BasicStroke ( 1.0f );

            case CommandNames.GROSOR_3PX:
                return new BasicStroke ( 3.0f );

            case CommandNames.GROSOR_5PX:
                return new BasicStroke ( 5.0f );

            case CommandNames.GROSOR_7PX:
                return new BasicStroke ( 7.0f );
            default:
                return new BasicStroke ( 1.0f );
        }
    }

    private int intGrosor ()
    {

        switch ( this.thin )
        {
            case CommandNames.GROSOR_1PX:
                return 1;

            case CommandNames.GROSOR_3PX:
                return 3;

            case CommandNames.GROSOR_5PX:
                return 5;

            case CommandNames.GROSOR_7PX:
                return 7;
            default:
                return 1;
        }
    }

    @Override
    public void pintar ( Graphics g )
    {

        if ( this.points.size () == 1 )
        {
            Graphics2D g2d = ( Graphics2D ) g;
            RenderingHints hi = new RenderingHints ( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
            g2d.addRenderingHints ( hi );
            g2d.addRenderingHints ( new RenderingHints ( RenderingHints.KEY_STROKE_CONTROL , RenderingHints.VALUE_STROKE_NORMALIZE ) );
            Point point = this.points.get ( 0 );
            g2d.setColor ( this.color );
            g2d.setStroke ( CreateGrosor () );
            g2d.drawLine ( point.x , point.y , point.x , point.y ); 
        }
        else if ( linea == true )
        {
            Graphics2D g2d = ( Graphics2D ) g;
            RenderingHints hi = new RenderingHints ( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );

            g2d.setColor ( this.color );
            g2d.addRenderingHints ( hi );
            g2d.addRenderingHints ( new RenderingHints ( RenderingHints.KEY_STROKE_CONTROL , RenderingHints.VALUE_STROKE_NORMALIZE ) );
            g2d.setStroke(CreateGrosor());
            double ty =  - (pointFinal.y - pointInicio.y) * 1.0;
            double tx = (pointFinal.x - pointInicio.x) * 1.0;
            double theta = Math.atan ( ty / tx );
            double angSep = 45.0;
            switch ( optionsLine )

            {
                case CommandNames.LINE_SQUARE:
                    if ( (pointFinal.x - pointInicio.x) < 0 )
                    {
                        theta += Math.PI;
                    }
                    if ( thin.equals ( CommandNames.GROSOR_1PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 4 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 4 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 4 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 4 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p3x = ( int ) (pointInicio.x - 4 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p3y = ( int ) (pointInicio.y + 4 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x - 4 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p4y = ( int ) (pointInicio.y + 4 * Math.sin ( theta + Math.toRadians ( angSep ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p3x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p3y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 4 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_3PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 8 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 8 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 8 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 8 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p3x = ( int ) (pointInicio.x - 8 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p3y = ( int ) (pointInicio.y + 8 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x - 8 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p4y = ( int ) (pointInicio.y + 8 * Math.sin ( theta + Math.toRadians ( angSep ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p3x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p3y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 4 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_5PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 12 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 12 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 12 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 12 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p3x = ( int ) (pointInicio.x - 12 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p3y = ( int ) (pointInicio.y + 12 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x - 12 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p4y = ( int ) (pointInicio.y + 12 * Math.sin ( theta + Math.toRadians ( angSep ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p3x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p3y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 4 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_7PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 16 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 16 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 16 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 16 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p3x = ( int ) (pointInicio.x - 16 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p3y = ( int ) (pointInicio.y + 16 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x - 16 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p4y = ( int ) (pointInicio.y + 16 * Math.sin ( theta + Math.toRadians ( angSep ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p3x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p3y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 4 );
                    }
                    break;
                case CommandNames.LINE_RHOMBUS:
                    ty =  - (pointFinal.y - pointInicio.y) * 1.0;
                    tx = (pointFinal.x - pointInicio.x) * 1.0;
                    theta = Math.atan ( ty / tx );
                    angSep = 90.0;
                    if ( (pointFinal.x - pointInicio.x) < 0 )
                    {
                        theta += Math.PI;
                    }
                    if ( thin.equals ( CommandNames.GROSOR_1PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 4 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 4 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 4 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 4 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p3x = ( int ) (pointInicio.x + 4 * Math.cos ( theta - Math.toRadians ( 0.0 ) ));
                        int p3y = ( int ) (pointInicio.y - 4 * Math.sin ( theta - Math.toRadians ( 0.0 ) ));
                        int p4x = ( int ) (pointInicio.x + 4 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 4 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p3x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p3y , p2y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 4 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_3PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 8 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 8 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 8 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 8 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p3x = ( int ) (pointInicio.x + 8 * Math.cos ( theta - Math.toRadians ( 0.0 ) ));
                        int p3y = ( int ) (pointInicio.y - 8 * Math.sin ( theta - Math.toRadians ( 0.0 ) ));
                        int p4x = ( int ) (pointInicio.x + 8 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 8 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p3x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p3y , p2y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 4 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_5PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 12 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 12 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 12 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 12 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p3x = ( int ) (pointInicio.x + 12 * Math.cos ( theta - Math.toRadians ( 0.0 ) ));
                        int p3y = ( int ) (pointInicio.y - 12 * Math.sin ( theta - Math.toRadians ( 0.0 ) ));
                        int p4x = ( int ) (pointInicio.x + 12 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 12 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p3x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p3y , p2y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 4 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_7PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 16 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 16 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 16 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 16 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p3x = ( int ) (pointInicio.x + 16 * Math.cos ( theta - Math.toRadians ( 0.0 ) ));
                        int p3y = ( int ) (pointInicio.y - 16 * Math.sin ( theta - Math.toRadians ( 0.0 ) ));
                        int p4x = ( int ) (pointInicio.x + 16 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 16 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p3x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p3y , p2y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 4 );
                    }
                    break;
                case CommandNames.LINE_CIRCLE:
                    if ( thin.equals ( CommandNames.GROSOR_1PX ) )
                    {
                        g2d.fillOval ( pointInicio.x - 3 , pointInicio.y - 3 , 6 , 6 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_3PX ) )
                    {
                        g2d.fillOval ( pointInicio.x - 5 , pointInicio.y - 5 , 10 , 10 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_5PX ) )
                    {
                        g2d.fillOval ( pointInicio.x - 7 , pointInicio.y - 7 , 14 , 14 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_7PX ) )
                    {
                        g2d.fillOval ( pointInicio.x - 9 , pointInicio.y - 9 , 18 , 18 );
                    }
                    break;
                case CommandNames.LINE_ARROW:
                    ty =  - (pointFinal.y - pointInicio.y) * 1.0;
                    tx = (pointFinal.x - pointInicio.x) * 1.0;
                    theta = Math.atan ( ty / tx );
                    angSep = 90.0;
                    if ( (pointFinal.x - pointInicio.x) < 0 )
                    {
                        theta += Math.PI;
                    }
                    if ( thin.equals ( CommandNames.GROSOR_1PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 4 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 4 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 4 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 4 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x + 4 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 4 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p4y
                        };
                        g2d.drawPolygon ( xPoints2 , yPoints2 , 3 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_3PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 8 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 8 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 8 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 8 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x + 8 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 8 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p4y
                        };
                        g2d.drawPolygon ( xPoints2 , yPoints2 , 3 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_5PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 12 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 12 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 12 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 12 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x + 12 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 12 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p4y
                        };
                        g2d.drawPolygon( xPoints2 , yPoints2 , 3 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_7PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 16 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 16 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 16 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 16 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x + 16 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 16 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p4y
                        };
                        g2d.drawPolygon ( xPoints2 , yPoints2 , 3 );
                    }
                    break;
                case CommandNames.LINE_ARROW_FILL:
                    ty =  - (pointFinal.y - pointInicio.y) * 1.0;
                    tx = (pointFinal.x - pointInicio.x) * 1.0;
                    theta = Math.atan ( ty / tx );
                    angSep = 90.0;
                    if ( (pointFinal.x - pointInicio.x) < 0 )
                    {
                        theta += Math.PI;
                    }
                    if ( thin.equals ( CommandNames.GROSOR_1PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 4 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 4 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 4 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 4 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x + 4 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 4 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 3 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_3PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 8 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 8 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 8 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 8 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x + 8 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 8 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 3 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_5PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 12 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 12 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 12 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 12 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x + 12 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 12 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 3 );
                    }
                    if ( thin.equals ( CommandNames.GROSOR_7PX ) )
                    {
                        int p1x = ( int ) (pointInicio.x + 16 * Math.cos ( theta - Math.toRadians ( angSep ) ));
                        int p1y = ( int ) (pointInicio.y - 16 * Math.sin ( theta - Math.toRadians ( angSep ) ));
                        int p2x = ( int ) (pointInicio.x + 16 * Math.cos ( theta + Math.toRadians ( angSep ) ));
                        int p2y = ( int ) (pointInicio.y - 16 * Math.sin ( theta + Math.toRadians ( angSep ) ));
                        int p4x = ( int ) (pointInicio.x + 16 * Math.cos ( theta + Math.toRadians ( 180.0 ) ));
                        int p4y = ( int ) (pointInicio.y - 16 * Math.sin ( theta + Math.toRadians ( 180.0 ) ));

                        int[] xPoints2 =
                        {
                            p1x , p2x , p4x
                        };
                        int[] yPoints2 =
                        {
                            p1y , p2y , p4y
                        };
                        g2d.fillPolygon ( xPoints2 , yPoints2 , 3 );
                    }
                    break;

            }
            
            
            if ( styleLine.equals ( CommandNames.ICON_Linea ) )
            {
                //linea normal
                g2d.setStroke ( CreateGrosor () );
                //g2d.drawLine ( pointInicio.x , pointInicio.y , pointFinal.x , pointFinal.y );
                
            }
            if ( styleLine.equals ( CommandNames.ICON_LineaSegmentada ) )
            {
                //linea segmentada
                float[] dash =
                {
                    15
                };
                g2d.setStroke ( new BasicStroke ( intGrosor () , BasicStroke.CAP_BUTT , BasicStroke.JOIN_MITER , 5.0f , dash , 0.0f ) );
                //g2d.drawLine ( pointInicio.x , pointInicio.y , pointFinal.x , pointFinal.y );
                //g2d.setStroke ( CreateGrosor () );
            }
            if ( styleLine.equals ( CommandNames.ICON_LineaPunteada ) )
            {
                //linea punteada
                float[] dash =
                {
                    intGrosor ()
                };
                g2d.setStroke ( new BasicStroke ( intGrosor () , BasicStroke.CAP_BUTT , BasicStroke.JOIN_MITER , 5.0f , dash , 0.0f ) );
                //g2d.drawLine ( pointInicio.x , pointInicio.y , pointFinal.x , pointFinal.y );
                //g2d.setStroke ( CreateGrosor () );
            }

            if(styleLine.equals ( CommandNames.ICON_LineaCompuesta)&& conector.equals ( CommandNames.ICON_LineaNormal))
            {
                //linea compuesta
                
                BasicStroke CreateGrosor = CreateGrosor();
                float lineWidth = CreateGrosor.getLineWidth();
                
                CreateGrosor = new BasicStroke(lineWidth/2);
                g2d.setStroke(CreateGrosor);
                double L = Math.sqrt((pointInicio.x-pointFinal.x)*(pointInicio.x-pointFinal.x)+(pointInicio.y-pointFinal.y)*(pointInicio.y-pointFinal.y));
                float offsetPixels ;
                if ( thin.equals ( CommandNames.GROSOR_7PX ) )
                {
                    offsetPixels= 8.0f;
                }
                else if ( thin.equals ( CommandNames.GROSOR_5PX ) )
                {
                    offsetPixels= 6.0f;
                }
                else if ( thin.equals ( CommandNames.GROSOR_3PX ) )
                {
                    offsetPixels= 4.0f;
                }
                else
                {
                    offsetPixels= 2.0f;
                }
                int x1p = (int) (pointInicio.x + offsetPixels * ((pointFinal.y-pointInicio.y) / L));
                int x2p = (int) (pointFinal.x + offsetPixels * ((pointFinal.y-pointInicio.y) / L));
                int y1p = (int) (pointInicio.y + offsetPixels * ((pointInicio.x-pointFinal.x) / L));
                int y2p = (int) (pointFinal.y + offsetPixels * ((pointInicio.x-pointFinal.x) / L));
                g2d.drawLine(x1p,y1p,x2p,y2p); 
                g2d.setStroke(CreateGrosor());
            }
            if(conector.equals ( CommandNames.ICON_LineaCurva))
            {
                //linea curva
                
                if ( optionsLine.equals(CommandNames.LINE_ARROW) || optionsLine.equals(CommandNames.LINE_ARROW_FILL))
                {
                    g2d.rotate(45.5,pointInicio.x,pointInicio.y);
                }
                //g2d.setStroke(CreateGrosor());
                double angle = Math.atan2(pointFinal.y - pointInicio.y, pointFinal.x - pointInicio.x);
                int diameter = (int) Math.round(pointInicio.distance(pointFinal));
                g2d.translate(pointInicio.x, pointInicio.y);
                g2d.rotate(angle);
                g2d.drawArc(0, -diameter/2, diameter, diameter, 0, 180);
                if(styleLine.equals ( CommandNames.ICON_LineaCompuesta))
                {
                    g2d.translate(-pointInicio.x, -pointInicio.y);
                    BasicStroke CreateGrosor = CreateGrosor();
                    float lineWidth = CreateGrosor.getLineWidth();

                    CreateGrosor = new BasicStroke(lineWidth/2);
                    g2d.setStroke(CreateGrosor);
                    double L = Math.sqrt((pointInicio.x-pointFinal.x)*(pointInicio.x-pointFinal.x)+(pointInicio.y-pointFinal.y)*(pointInicio.y-pointFinal.y));
                    float offsetPixels ;
                    if ( thin.equals ( CommandNames.GROSOR_7PX ) )
                    {
                        offsetPixels= 9.0f;
                    }
                    else if ( thin.equals ( CommandNames.GROSOR_5PX ) )
                    {
                        offsetPixels= 7.0f;
                    }
                    else if ( thin.equals ( CommandNames.GROSOR_3PX ) )
                    {
                        offsetPixels= 5.0f;
                    }
                    else
                    {
                        offsetPixels= 3.0f;
                    }
                    int x1p = (int) (pointInicio.x + offsetPixels * ((pointFinal.y-pointInicio.y) / L));
                    int x2p = (int) (pointFinal.x + offsetPixels * ((pointFinal.y-pointInicio.y) / L));
                    int y1p = (int) (pointInicio.y + offsetPixels * ((pointInicio.x-pointFinal.x) / L));
                    int y2p = (int) (pointFinal.y + offsetPixels * ((pointInicio.x-pointFinal.x) / L));
                    
                    angle = Math.atan2(y2p - y1p, x2p - x1p);
                    diameter = (int) Math.round(new Point(x1p, y1p).distance(new Point(x2p, y2p)));
                    g2d.translate(x1p, y1p);
                    g2d.drawArc(0, -diameter/2, diameter, diameter, 0, 180);
                    g2d.setStroke(CreateGrosor());
                }
            }
            if(conector.equals ( CommandNames.ICON_LineaAngular))
            {
                //linea curva
                
                if(styleLine.equals ( CommandNames.ICON_LineaCompuesta))
                {
                    
                    BasicStroke CreateGrosor = CreateGrosor();
                    float lineWidth = CreateGrosor.getLineWidth();

                    CreateGrosor = new BasicStroke(lineWidth/2);
                    g2d.setStroke(CreateGrosor);
                    double L = Math.sqrt((pointInicio.x-pointFinal.x)*(pointInicio.x-pointFinal.x)+(pointInicio.y-pointFinal.y)*(pointInicio.y-pointFinal.y));
                    float offsetPixels ;
                    if ( thin.equals ( CommandNames.GROSOR_7PX ) )
                    {
                        offsetPixels= 9.0f;
                    }
                    else if ( thin.equals ( CommandNames.GROSOR_5PX ) )
                    {
                        offsetPixels= 7.0f;
                    }
                    else if ( thin.equals ( CommandNames.GROSOR_3PX ) )
                    {
                        offsetPixels= 5.0f;
                    }
                    else
                    {
                        offsetPixels= 3.0f;
                    }
                    int x1p = (int) (pointInicio.x + offsetPixels * ((pointFinal.y-pointInicio.y) / L));
                    int x2p = (int) (pointFinal.x + offsetPixels * ((pointFinal.y-pointInicio.y) / L));
                    int y1p = (int) (pointInicio.y + offsetPixels * ((pointInicio.x-pointFinal.x) / L));
                    int y2p = (int) (pointFinal.y + offsetPixels * ((pointInicio.x-pointFinal.x) / L));
                    g2d.drawLine ( x1p , y1p , ( x1p +x2p)/2 , y1p );
                    g2d.drawLine ( ( x1p +x2p)/2 , y1p, ( x1p +x2p)/2 , y2p);
                    g2d.drawLine ( ( x1p +x2p)/2 , y2p ,x2p , y2p); 
                    g2d.setStroke(CreateGrosor());
                }
                g2d.drawLine ( pointInicio.x , pointInicio.y , (pointInicio.x +pointFinal.x)/2 , pointInicio.y );
                g2d.drawLine ( (pointInicio.x +pointFinal.x)/2 , pointInicio.y, (pointInicio.x +pointFinal.x)/2 , pointFinal.y );
                g2d.drawLine ( (pointInicio.x +pointFinal.x)/2 , pointFinal.y ,pointFinal.x , pointFinal.y);
            }
            if(conector.equals ( CommandNames.ICON_LineaNormal))
            {
                //linea curva
                
                //g2d.setStroke(CreateGrosor());
                g2d.drawLine ( pointInicio.x , pointInicio.y , pointFinal.x, pointFinal.y );
            }

        }
        else
        {
            for ( int i = 0 ; i < this.points.size () - 1 ; i ++ )
            {
                Graphics2D g2d = ( Graphics2D ) g;
                RenderingHints hi = new RenderingHints ( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );

                g2d.setColor ( this.color );
                g2d.setStroke ( CreateGrosor () );
                g2d.addRenderingHints ( hi );
                //g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE));

                Point p1 = this.points.get ( i );
                Point p2 = this.points.get ( i + 1 );

                // para que dibuje mas grueso jajajjaja.... :)
                if ( brush )
                {
                    //g2d.setStroke(new BasicStroke(6.0f));
                    g2d.drawLine ( ( int ) p1.x + 1 , ( int ) p1.y + 1 , ( int ) p2.x + 1 , ( int ) p2.y + 1 );
                    g2d.drawLine ( ( int ) p1.x + 2 , ( int ) p1.y + 2 , ( int ) p2.x + 2 , ( int ) p2.y + 2 );
                    g2d.drawLine ( ( int ) p1.x , ( int ) p1.y , ( int ) p2.x , ( int ) p2.y );
                    g2d.drawLine ( ( int ) p1.x - 1 , ( int ) p1.y - 1 , ( int ) p2.x - 1 , ( int ) p2.y - 1 );
                    g2d.drawLine ( ( int ) p1.x - 2 , ( int ) p1.y - 2 , ( int ) p2.x - 2 , ( int ) p2.y - 2 );

                }
                else
                {
                    g2d.drawLine ( ( int ) p1.x , ( int ) p1.y , ( int ) p2.x , ( int ) p2.y );
                }
            }
        }

    }
}
