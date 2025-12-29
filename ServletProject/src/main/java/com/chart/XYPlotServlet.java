package com.chart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

@WebServlet("/XYPlot")
public class XYPlotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_DRV = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String DB_ID = "scott";
	private static final String DB_PW = "tiger";
	
	public void init() throws ServletException {
		try {
			Class.forName(DB_DRV);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			stmt = con.createStatement();
			String sql = "select * from stock";
			rs = stmt.executeQuery(sql);
			XYSeries vs = new XYSeries("Stock Flow");
			int x = 1;
			
			while (rs.next()) {
				vs.add(x, rs.getInt(2));
				x++;
			}
			
			XYSeriesCollection ds = new XYSeriesCollection(vs);
			
			JFreeChart chart = ChartFactory.createXYLineChart("Stock Average", "Day", "Price", ds, PlotOrientation.VERTICAL, true, true, false);
			
			ServletOutputStream out = response.getOutputStream();
			ChartUtilities.writeChartAsPNG(out, chart, 500, 500);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
