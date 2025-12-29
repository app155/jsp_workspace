package com.chart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/*
 * 	차트 작성 순서
 * 	1. 데이터 생성
 * 		- Dataset 인터페이스를 구현한 클래스를 통해 만들어짐
 * 
 * 	2. 차트 생성
 * 		- ChartFactory 클래스의 createXXXChart() 메소드를 통해 만들어짐
 * 
 * 	3. 차트를 원하는 형태로 커스터마이징한다.
 * 
 * 	4. 차트를 보여준다.
 * 		- ServletOutputStream 클래스와 ChartUtilities 클래스를 사용해 출력
 */

@WebServlet("/PieChart")
public class PieChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Son-OGong", 63.7);
		dataset.setValue("Jer-PalGae", 37.9);
		dataset.setValue("Sa-OJung", 29.5);
		
		JFreeChart chart = ChartFactory.createPieChart("EnergyPower", dataset, true, true, false);
		
		ServletOutputStream out = response.getOutputStream();
		ChartUtilities.writeChartAsPNG(out, chart, 500, 500);
	}

}
