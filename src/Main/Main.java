package Main;

import Methods.Filtros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Main {

    private JFrame frame;
    private JLabel imageLabel;
    private JButton carregarImagem;
    private JButton realceDeBordaBotao;
    private  JButton suavizar;
    private JButton Binarizar;
    private JButton InverterCores;
    private JButton aplicarFiltro1;
    private JButton aplicarFiltro2;
    private JButton aplicarFiltro3;
    private JButton aplicarFiltro4;
    private JButton aplicarFiltro5;
    private JButton aplicarFiltro6;
    private JButton aplicarFiltro7;
    private BufferedImage imagem;

    public Main() {
        frame = new JFrame("Filtros de Imagem");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        imageLabel = new JLabel();
        JScrollPane scrollPane = new JScrollPane(imageLabel);
        frame.add(scrollPane, BorderLayout.CENTER);


        JPanel barraDeBotoesCima = new JPanel();
        aplicarFiltro1 = new JButton("Aplicar Filtro 1");
        aplicarFiltro2 = new JButton("Aplicar Filtro 2");
        aplicarFiltro3 = new JButton("Aplicar Filtro 3");
        aplicarFiltro4 = new JButton("Aplicar Filtro 4");
        aplicarFiltro5 = new JButton("Aplicar Filtro 5");
        aplicarFiltro6 = new JButton("Aplicar Filtro 6");
        aplicarFiltro7 = new JButton("Aplicar Filtro 7");

        barraDeBotoesCima.add(aplicarFiltro1);
        barraDeBotoesCima.add(aplicarFiltro2);
        barraDeBotoesCima.add(aplicarFiltro3);
        barraDeBotoesCima.add(aplicarFiltro4);
        barraDeBotoesCima.add(aplicarFiltro5);
        barraDeBotoesCima.add(aplicarFiltro6);
        barraDeBotoesCima.add(aplicarFiltro7);


        JPanel barraDeBotoesBaixo = new JPanel();
        carregarImagem = new JButton("Carregar Imagem");
        realceDeBordaBotao = new JButton("Realce de Borda");
        suavizar = new JButton("Suavizar");
        Binarizar = new JButton("Binarizar");
        InverterCores = new JButton("Inverter Cores");

        barraDeBotoesBaixo.add(carregarImagem);
        barraDeBotoesBaixo.add(realceDeBordaBotao);
        barraDeBotoesBaixo.add(suavizar);
        barraDeBotoesBaixo.add(Binarizar);
        barraDeBotoesBaixo.add(InverterCores);

        frame.add(barraDeBotoesCima, BorderLayout.NORTH);
        frame.add(barraDeBotoesBaixo, BorderLayout.SOUTH);

        carregarImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        imagem = ImageIO.read(selectedFile);
                        exibirImagem(imagem);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        realceDeBordaBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;
                    try {
                        imagemSaida = Filtros.realceBorda(imagem);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        Binarizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    int limiar = 128;
                    BufferedImage imagemSaida = null;
                    try {
                        imagemSaida = Filtros.binarizarImagem(imagem, limiar);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        InverterCores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;
                    try {
                        imagemSaida = Filtros.inverterCores(imagem);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        suavizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;
                    try {
                        imagemSaida = Filtros.suavizar(imagem);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        aplicarFiltro1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    int[] filter1 = {
                            1, 1, 1,
                            0, 0, 0,
                            -1, -1, -1
                    };

                    try {
                        imagemSaida = Filtros.aplicarFiltros(imagem, filter1);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });

        aplicarFiltro2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    int[] filter2 = {
                            1, 0, -1,
                            1, 0, -1,
                            1, 0, -1
                    };

                    try {
                        imagemSaida = Filtros.aplicarFiltros(imagem, filter2);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });
        aplicarFiltro3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    int[] filter3 = {
                            -1, -1, -1,
                            -1, 8, -1,
                            -1, -1, -1
                    };

                    try {
                        imagemSaida = Filtros.aplicarFiltros(imagem, filter3);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });


        aplicarFiltro4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    int[] filter4 = {
                            0, -1, 0,
                            -1, 5, -1,
                            0, -1, 0
                    };

                    try {
                        imagemSaida = Filtros.aplicarFiltros(imagem, filter4);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });


        aplicarFiltro5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    int[] filter5 = {
                            0, 0, 0,
                            -1, 1, 0,
                            0, 0, 0
                    };

                    try {
                        imagemSaida = Filtros.aplicarFiltros(imagem, filter5);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });


        aplicarFiltro6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    int[] filter6 = {
                            0, 1, 0,
                            1, -4, 1,
                            0, 1, 0
                    };

                    try {
                        imagemSaida = Filtros.aplicarFiltros(imagem, filter6);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });


       aplicarFiltro7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagem != null) {
                    BufferedImage imagemSaida = null;

                    int[] filter8 = {
                            -2, -1, 0,
                            -1, 1, 1,
                            0, 1, 2
                    };

                    try {
                        imagemSaida = Filtros.aplicarFiltros(imagem, filter8);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    exibirImagem(imagemSaida);
                }
            }
        });


        frame.setVisible(true);
    }

    private void exibirImagem(BufferedImage image) {
        ImageIcon imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
        imageLabel.revalidate();
        imageLabel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
