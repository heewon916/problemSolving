package com.ssafy.yumyumcoach.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ssafy.yumyumcoach.model.dto.DietLogDto;
import com.ssafy.yumyumcoach.model.dto.DietLogSearchDto;
import com.ssafy.yumyumcoach.model.dto.DietLogSearchDto.SearchType;
import com.ssafy.yumyumcoach.model.dto.FoodDto;
import com.ssafy.yumyumcoach.model.service.DietLogService;
import com.ssafy.yumyumcoach.model.service.DietLogServiceImpl;

/**
 * <h2>CoachInfoView</h2>
 * <p>Swing 기반 식단 조회·검색 뷰.<br>
 *    실행 시 전체 식단을 날짜순으로 나열하고<br>
 *    드롭다운(날짜/음식) 검색 및 리스트 선택으로 상세 정보를 표시한다.</p>
 */
public class CoachInfoView {

    /* ──────────── UI 컴포넌트 ──────────── */
    private final JFrame frame      = new JFrame("YumYumCoach - 식단 정보");
    private final JComboBox<String> searchTypeCmb =
            new JComboBox<>(new String[]{"날짜", "음식"});
    private final JTextField searchField  = new JTextField();
    private final JButton    searchBtn    = new JButton("검색");

    private final JLabel imageLabel  = new JLabel();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> listAll              = new JList<>(listModel);

    private final DefaultListModel<String> foodModel = new DefaultListModel<>();
    private final JList<String> listFoods            = new JList<>(foodModel);

    private final JLabel idLabel          = new JLabel();
    private final JLabel dateMealLabel    = new JLabel();
    private final JLabel totWeightLabel   = new JLabel();
    private final JLabel totEnergyLabel   = new JLabel();
    private final JLabel totCarbLabel     = new JLabel();
    private final JLabel totProteinLabel  = new JLabel();
    private final JLabel totFatLabel      = new JLabel();

    private final ImageIcon mealIcon = new ImageIcon("img/SampleMeal.png");

    /* ──────────── 데이터 ──────────── */
    private final DietLogService service = new DietLogServiceImpl();
    private List<DietLogDto> diets;   // 정렬된 전체 리스트

    /** 뷰 생성자 : 데이터 로드 → UI 구성 → 첫 항목 표시 */
    public CoachInfoView() {
        loadAndSortData();
        buildUI();
        populateList(diets);

        if (!listModel.isEmpty()) {
            listAll.setSelectedIndex(0);
            showDetail(listAll.getSelectedValue());
        }
        frame.setVisible(true);
    }

    /* ───────────────────────── 데이터 로드 & 정렬 ───────────────────────── */
    private void loadAndSortData() {
    	  // 1) DAO에서 전체 불변 리스트 가져오기
        List<DietLogDto> loaded = service.searchAll(new DietLogSearchDto());
        
        // 2) 가변 ArrayList로 복사
        diets = new ArrayList<>(loaded);

        // 3) 날짜 & 식사구분 정렬
        List<String> order = Arrays.asList("아침", "점심", "저녁", "간식");
        diets.sort((a, b) -> {
            int cmp = a.getDate().compareTo(b.getDate());
            return (cmp != 0)
                   ? cmp
                   : Integer.compare(order.indexOf(a.getMealType()),
                                     order.indexOf(b.getMealType()));
        });
    }

    /* ───────────────────────────── UI 구성 ───────────────────────────── */
    private void buildUI() {
        /* Frame 기본 설정 */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        /* 상단 검색바 */
        JPanel searchBar = new JPanel(new BorderLayout(5,0));
        searchBar.setBorder(new EmptyBorder(10,10,10,10));
        searchBar.add(searchTypeCmb, BorderLayout.WEST);
        searchBar.add(searchField,    BorderLayout.CENTER);
        searchBar.add(searchBtn,      BorderLayout.EAST);
        frame.add(searchBar, BorderLayout.NORTH);

        /* 중앙 패널 (이미지 | 상세 | 리스트) */
        JPanel center = new JPanel(new BorderLayout(10,0));
        center.setBorder(new EmptyBorder(0,10,10,10));
        frame.add(center, BorderLayout.CENTER);

        /* 좌측 식단 이미지 */
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imageLabel.setPreferredSize(new Dimension(300,0));
        imageLabel.setIcon(mealIcon);
        center.add(imageLabel, BorderLayout.WEST);

        /* 우측 식단 목록 */
        JScrollPane spAll = new JScrollPane(listAll);
        spAll.setPreferredSize(new Dimension(200,0));
        spAll.setBorder(BorderFactory.createTitledBorder("식단 목록"));
        center.add(spAll, BorderLayout.EAST);

        /* 중앙 상세 패널 */
        JPanel detail = new JPanel();
        detail.setLayout(new BoxLayout(detail, BoxLayout.Y_AXIS));
        center.add(detail, BorderLayout.CENTER);

        idLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateMealLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detail.add(idLabel);
        detail.add(Box.createVerticalStrut(8));
        detail.add(dateMealLabel);
        detail.add(Box.createVerticalStrut(8));

        /* 음식 리스트 */
        JScrollPane spFoods = new JScrollPane(listFoods);
        spFoods.setBorder(BorderFactory.createTitledBorder("음식 목록"));
        detail.add(spFoods);
        detail.add(Box.createVerticalStrut(8));

        /* 요약 패널 */
        JPanel summary = new JPanel(new GridLayout(5,1,0,4));
        summary.setBorder(BorderFactory.createTitledBorder("총 섭취 요약"));
        summary.add(totWeightLabel);
        summary.add(totEnergyLabel);
        summary.add(totCarbLabel);
        summary.add(totProteinLabel);
        summary.add(totFatLabel);
        detail.add(summary);

        /* 이벤트 */
        // complete code #01
        // searchBtn 에 addActionListener 을 이용하여 검색 기능 처리 기능 완성하기
       
    }

    /* ───────────────────────────── 검색 ───────────────────────────── */
    private void doSearch() {
        String kw   = searchField.getText().trim();
        String type = (String) searchTypeCmb.getSelectedItem();

        DietLogSearchDto cond;
        if (kw.isBlank()) {
            cond = new DietLogSearchDto();                // ALL
        } else if ("날짜".equals(type)) {
            cond = new DietLogSearchDto(SearchType.DATE, kw);
        } else {
            cond = new DietLogSearchDto(SearchType.FOOD, kw);
        }

        List<DietLogDto> result = service.searchAll(cond);
        populateList(result);

        if (!listModel.isEmpty()) {
            listAll.setSelectedIndex(0);
            showDetail(listAll.getSelectedValue());
        }
    }

    /* ───────────────────────────── 리스트 표시 ───────────────────────────── */
    private void populateList(List<DietLogDto> list) {
        listModel.clear();
        list.forEach(d -> listModel.addElement(d.getDate() + " / " + d.getMealType()));
    }

    /* ───────────────────────────── 상세 표시 ───────────────────────────── */
    private void showDetail(String key) {
        if (key == null) return;
        String[] p = key.split(" / ");
        String date = p[0], meal = p[1];

        DietLogDto dto = diets.stream()
                              .filter(d -> d.getDate().equals(date)
                                        && d.getMealType().equals(meal))
                              .findFirst().orElse(null);
        if (dto == null) return;

        idLabel.setText("식단 ID: " + dto.getDietLogId());
        dateMealLabel.setText("날짜 / 식사구분: " + date + " / " + meal);

        /* 음식 리스트 & 요약 계산 */
        foodModel.clear();
        int   totalW = 0;
        double totE=0, totC=0, totP=0, totF=0;
        for (FoodDto f : dto.getFoods()) {
            foodModel.addElement(String.format("%s (%s)", f.getName(), f.getWeight()));
            totalW += f.getWeightGram();
            totE   += f.getEnergy();
            totC   += f.getCarb();
            totP   += f.getProtein();
            totF   += f.getFat();
        }
        totWeightLabel.setText(String.format("총 중량: %d g", totalW));
        totEnergyLabel.setText(String.format("총 칼로리: %.2f kcal", totE));
        totCarbLabel.setText(String.format("총 탄수화물: %.2f g", totC));
        totProteinLabel.setText(String.format("총 단백질: %.2f g", totP));
        totFatLabel.setText(String.format("총 지방: %.2f g", totF));
    }
}
