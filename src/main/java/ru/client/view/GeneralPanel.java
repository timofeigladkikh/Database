package ru.client.view;

import ru.client.DbSource;
import ru.client.model.*;
import ru.client.view.dialogs.*;
import ru.client.view.tablemodel.*;
import ru.client.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GeneralPanel extends JPanel implements Observer {

    private final ItpDAO itpDAO;
    private final WorkersDAO workersDAO;
    private final WorkshopsDAO workshopsDAO;
    private final SitesDAO sitesDAO;
    private final BrigadesDAO brigadesDAO;
    private final PostsItpDAO postsItpDAO;
    private final PostsWorkersDAO postsWorkersDAO;


    private final JFrame owner;

    private JButton addItpButton;
    private JButton deleteItpButton;
    private JButton addWorkshopButton;
    private JButton deleteWorkshopButton;
    private JButton addSitesButton;
    private JButton deleteSitesButton;
    private JButton addPostsItpButton;
    private JButton deletePostsItpButton;
    private JButton addPostsWorkersButton;
    private JButton deletePostsWorkersButton;
    private JButton addWorkerButton;
    private JButton deleteWorkerButton;
    private JButton addBrigadeButton;
    private JButton deleteBrigadeButton;

    private JTable itpTable;
    private JTable workshopsTable;
    private JTable sitesTable;
    private JTable postsItpTable;
    private JTable postsWorkersTable;
    private JTable workersTable;
    private JTable brigadesTable;


    private List<Itp> itp;
    private List<Workshops> workshops;
    private List<Sites> sites;
    private List<PostsItp> postsItp;
    private List<PostsWorkers> postsWorkers;
    private List<Workers> workers;
    private List<Brigades> brigades;

    private Map<Integer, String> workshopMap;
    private Map<String, Integer> workshopName;

    private Map<Integer, String> sitesMap;
    private Map<String, Integer> sitesName;

    private Map<Integer, String> postsItpMap;
    private Map<String, Integer> postsItpName;

    private Map<Integer, String> brigadesMap;
    private Map<String, Integer> brigadesName;

    private Map<Integer, String> postsWorkersMap;
    private Map<String, Integer> postsWorkersName;

    public GeneralPanel(JFrame owner) {
        this.owner = owner;
        workshopsDAO = new WorkshopsDAO(DbSource.getSql2o());
        itpDAO = new ItpDAO(DbSource.getSql2o());
        sitesDAO = new SitesDAO(DbSource.getSql2o());
        postsItpDAO = new PostsItpDAO(DbSource.getSql2o());
        postsWorkersDAO = new PostsWorkersDAO(DbSource.getSql2o());
        workersDAO = new WorkersDAO(DbSource.getSql2o());
        brigadesDAO = new BrigadesDAO(DbSource.getSql2o());

        itpTable = new JTable();
        workshopsTable = new JTable();
        sitesTable = new JTable();
        postsItpTable = new JTable();
        postsWorkersTable = new JTable();
        workersTable = new JTable();
        brigadesTable = new JTable();


        itpTable.setShowGrid(true);
        workshopsTable.setShowGrid(true);
        sitesTable.setShowGrid(true);
        postsItpTable.setShowGrid(true);
        brigadesTable.setShowGrid(true);
        workersTable.setShowGrid(true);

        itpTable.setGridColor(Color.lightGray);
        workshopsTable.setGridColor(Color.lightGray);
        sitesTable.setGridColor(Color.lightGray);
        postsItpTable.setGridColor(Color.lightGray);
        postsWorkersTable.setGridColor(Color.lightGray);
        workersTable.setGridColor(Color.lightGray);
        brigadesTable.setGridColor(Color.lightGray);

        refresh();

        addItpButton = new JButton("Добавить нового члена ИТП");

        DialogHandler<Itp> itpDialogHandler = entity -> {
            itpDAO.create(entity);
            refresh();
        };

        addItpButton.addActionListener(e -> {
            ItpDialog itpDialog = new ItpDialog(owner, itpDialogHandler, workshopName, sitesName, postsItpName);
        });

        deleteItpButton = new JButton("Удалить члена ИТП");

        DialogHandler<Integer> itpDeleteDialogHandler = entity -> {
            itpDAO.delete(entity);
            refresh();
        };

        deleteItpButton.addActionListener(e -> {
            DeleteDialog itpDeleteDialog = new DeleteDialog(owner, itpDeleteDialogHandler, "Удаление члена ИТП");
        });

        addWorkshopButton = new JButton("Добавить цех");

        DialogHandler<Workshops> workshopsDialogHandler = entity -> {
            workshopsDAO.create(entity);
            refresh();
        };

        addWorkshopButton.addActionListener(e -> {
            WorkshopsDialog workshopsDialog = new WorkshopsDialog(owner, workshopsDialogHandler);
        });

        deleteWorkshopButton = new JButton("Удалить цех");

        DialogHandler<Integer> workshopDeleteDialogHandler = entity -> {
            workshopsDAO.delete(entity);
            refresh();
        };

        deleteWorkshopButton.addActionListener(e -> {
            DeleteDialog deleteWorkshopDialog = new DeleteDialog(owner, workshopDeleteDialogHandler, "Удаление цеха");
        });

        addSitesButton = new JButton("Добавить участок");

        DialogHandler<Sites> sitesDialogHandler = entity -> {
            sitesDAO.create(entity);
            refresh();
        };

        addSitesButton.addActionListener(e -> {
            SitesDialog sitesDialog = new SitesDialog(owner, sitesDialogHandler, workshopName);
        });

        deleteSitesButton = new JButton("Удалить участок");

        DialogHandler<Integer> sitesDeleteDialogHandler = entity -> {
            sitesDAO.delete(entity);
            refresh();
        };

        deleteSitesButton.addActionListener(e -> {
            DeleteDialog deleteSitesDialog = new DeleteDialog(owner, sitesDeleteDialogHandler, "Удаление участка");
        });

        addBrigadeButton = new JButton("Добавить бригаду");

        DialogHandler<Brigades> brigadesDialogHandler = entity -> {
            brigadesDAO.create(entity);
            refresh();
        };

        addBrigadeButton.addActionListener(e -> {
            BrigadesDialog brigadesDialog = new BrigadesDialog(owner, brigadesDialogHandler, sitesName);
        });

        deleteBrigadeButton = new JButton("Удалить бригаду");

        DialogHandler<Integer> brigadesDeleteDialogHandler = entity -> {
            brigadesDAO.delete(entity);
            refresh();
        };

        deleteBrigadeButton.addActionListener(e -> {
            DeleteDialog deleteSitesDialog = new DeleteDialog(owner, brigadesDeleteDialogHandler, "Удаление бригады");
        });

        addPostsItpButton = new JButton("Добавить должность члена ИТП");

        DialogHandler<PostsItp> postsItpDialogHandler = entity -> {
            postsItpDAO.create(entity);
            refresh();
        };

        addPostsItpButton.addActionListener(e -> {
            PostsItpDialog postsItpDialog = new PostsItpDialog(owner, postsItpDialogHandler);
        });

        deletePostsItpButton = new JButton("Удалить должность для ИТП");

        DialogHandler<Integer> postsItpDeleteDialogHandler = entity -> {
            postsItpDAO.delete(entity);
            refresh();
        };

        deletePostsItpButton.addActionListener(e -> {
            DeleteDialog postsItpDeleteDialog = new DeleteDialog(owner, postsItpDeleteDialogHandler, "Удаление должности для ИТП");
        });

        addPostsWorkersButton = new JButton("Добавить должность для рабочих");

        DialogHandler<PostsWorkers> postsWorkersDialogHandler = entity -> {
            postsWorkersDAO.create(entity);
            refresh();
        };

        addPostsWorkersButton.addActionListener(e -> {
            PostsWorkersDialog postsWorkersDialog = new PostsWorkersDialog(owner, postsWorkersDialogHandler);
        });

        deletePostsWorkersButton = new JButton("Удалить должность для рабочих");

        DialogHandler<Integer> postsWorkersDeleteDialog = entity -> {
            postsWorkersDAO.delete(entity);
            refresh();
        };

        deletePostsWorkersButton.addActionListener(e -> {
            DeleteDialog postsItpDeleteDialog = new DeleteDialog(owner, postsWorkersDeleteDialog, "Удаление должности рабочих");
        });

        addWorkerButton = new JButton("Добавить рабочего");

        DialogHandler<Workers> workersDialogHandler = entity -> {
            workersDAO.create(entity);
            refresh();
        };

        addWorkerButton.addActionListener(e -> {
            WorkersDialog workersDialog = new WorkersDialog(owner, workersDialogHandler, brigadesName, postsWorkersName);
        });

        deleteWorkerButton = new JButton("Удалить рабочего");

        DialogHandler<Integer> workersDeleteDialogHandler = entity -> {
            workersDAO.delete(entity);
            refresh();
        };

        deleteWorkerButton.addActionListener(e -> {
            DeleteDialog workersDeleteDialog = new DeleteDialog(owner, workersDeleteDialogHandler, "Удаление рабочего");
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /*add(addItpButton);
        add(deleteItpButton);
        add(itpTable.getTableHeader());
        add(new JScrollPane(itpTable));
        add(Box.createVerticalGlue());

        add(addWorkerButton);
        add(deleteWorkerButton);
        add(workersTable.getTableHeader());
        add(new JScrollPane(workersTable));
        add(Box.createVerticalGlue());*/

        /*add(addWorkshopButton);
        add(deleteWorkshopButton);
        add(workshopsTable.getTableHeader());
        add(new JScrollPane(workshopsTable));
        add(Box.createVerticalGlue());

        add(addSitesButton);
        add(deleteSitesButton);
        add(sitesTable.getTableHeader());
        add(new JScrollPane(sitesTable));
        add(Box.createVerticalGlue());

        add(addBrigadeButton);
        add(deleteBrigadeButton);
        add(brigadesTable.getTableHeader());
        add(new JScrollPane(brigadesTable));
        add(Box.createVerticalGlue());

        add(addPostsItpButton);
        add(deletePostsItpButton);
        add(postsItpTable.getTableHeader());
        add(new JScrollPane(postsItpTable));
        add(Box.createVerticalGlue());

        add(addPostsWorkersButton);
        add(deletePostsWorkersButton);
        add(postsWorkersTable.getTableHeader());
        add(new JScrollPane(postsWorkersTable));
        add(Box.createVerticalGlue());*/



    }

    private void refresh() {
        itp = itpDAO.getAll();
        workers = workersDAO.getAll();
        workshops = workshopsDAO.getAll();
        sites = sitesDAO.getAll();
        postsItp = postsItpDAO.getAll();
        postsWorkers = postsWorkersDAO.getAll();
        brigades = brigadesDAO.getAll();


        workshopMap = workshops.stream()
                .collect(Collectors.toMap(Workshops::getId, Workshops::getName));

        workshopName = workshops.stream()
                .collect(Collectors.toMap(Workshops::getName, Workshops::getId));

        sitesMap = sites.stream()
                .collect(Collectors.toMap(Sites::getId, Sites::getName));

        sitesName = sites.stream()
                .collect(Collectors.toMap(Sites::getName, Sites::getId));

        postsItpMap = postsItp.stream()
                .collect(Collectors.toMap(PostsItp::getId, PostsItp::getName));

        postsItpName = postsItp.stream()
                .collect(Collectors.toMap(PostsItp::getName, PostsItp::getId));

        postsWorkersMap = postsWorkers.stream()
                .collect(Collectors.toMap(PostsWorkers::getId, PostsWorkers::getName));

        postsWorkersName = postsWorkers.stream()
                .collect(Collectors.toMap(PostsWorkers::getName, PostsWorkers::getId));

        postsItpMap = postsItp.stream()
                .collect(Collectors.toMap(PostsItp::getId, PostsItp::getName));

        postsItpName = postsItp.stream()
                .collect(Collectors.toMap(PostsItp::getName, PostsItp::getId));

        brigadesMap = brigades.stream()
                .collect(Collectors.toMap(Brigades::getId, Brigades::getName));

        brigadesName = brigades.stream()
                .collect(Collectors.toMap(Brigades::getName, Brigades::getId));

        ItpTableModel itpTableModel = new ItpTableModel(itp, itpDAO, workshopMap, sitesMap, postsItpMap);
        WorkersTableModel workersTableModel = new WorkersTableModel(workers, workersDAO, brigadesMap, postsWorkersMap);
        WorkshopsTableModel workshopsTableModel = new WorkshopsTableModel(workshops, workshopsDAO);
        SitesTableModel sitesTableModel = new SitesTableModel(sites, sitesDAO, workshopMap);
        BrigadesTableModel brigadesTableModel = new BrigadesTableModel(brigades, brigadesDAO, sitesMap);
        PostsItpTableModel postsItpTableModel = new PostsItpTableModel(postsItp, postsItpDAO);
        PostsWorkersTableModel postsWorkersTableModel = new PostsWorkersTableModel(postsWorkers, postsWorkersDAO);

        workshopsTableModel.addListener(this);
        sitesTableModel.addListener(this);
        brigadesTableModel.addListener(this);
        postsItpTableModel.addListener(this);
        postsWorkersTableModel.addListener(this);

        itpTable.setModel(itpTableModel);
        workersTable.setModel(workersTableModel);
        workshopsTable.setModel(workshopsTableModel);
        sitesTable.setModel(sitesTableModel);
        postsItpTable.setModel(postsItpTableModel);
        postsWorkersTable.setModel(postsWorkersTableModel);
        brigadesTable.setModel(brigadesTableModel);

        String[] workshopsArray = workshops.stream()
                .map(Workshops::getName)
                .toArray(String[]::new);
        JComboBox comboBox1 = new JComboBox(workshopsArray);
        itpTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox1));
        sitesTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox1));

        String[] brigadesArray = brigades.stream()
                .map(Brigades::getName)
                .toArray(String[]::new);
        JComboBox comboBox5 = new JComboBox(brigadesArray);
        workersTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox5));

        String[] sitesArray = sites.stream()
                .map(Sites::getName)
                .toArray(String[]::new);
        JComboBox comboBox2 = new JComboBox(sitesArray);
        itpTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(comboBox2));
        brigadesTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox2));

        String[] postsItpArray = postsItp.stream()
                .map(PostsItp::getName)
                .toArray(String[]::new);
        JComboBox comboBox3 = new JComboBox(postsItpArray);
        itpTable.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(comboBox3));

        String[] postsWorkersArray = postsWorkers.stream()
                .map(PostsWorkers::getName)
                .toArray(String[]::new);
        JComboBox comboBox4 = new JComboBox(postsWorkersArray);
        workersTable.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(comboBox4));

        //виды изделий + продукция.ид для производства

    }

    @Override
    public void update() {
        refresh();
    }
}
