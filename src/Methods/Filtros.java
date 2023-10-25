package Methods;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Filtros {
    public static BufferedImage suavizar(BufferedImage entrada) throws IOException {
        int largura = entrada.getWidth();
        int altura = entrada.getHeight();

        BufferedImage saida = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        double[] filtro_gau = {
                0.0625, 0.125, 0.0625,
                0.125, 0.25, 0.125,
                0.0625, 0.125, 0.0625
        };

        Color px1, px2, px3, px4, px5, px6, px7, px8, px9;

        for (int linha = 1; linha < largura-2; linha++){
            for (int coluna = 1; coluna < altura-2; coluna++){

                px1 = new Color( entrada.getRGB(linha-1, coluna-1) );
                px2 = new Color( entrada.getRGB(linha-1, coluna) );
                px3 = new Color( entrada.getRGB(linha-1, coluna+1) );
                px4 = new Color( entrada.getRGB(linha, coluna-1) );
                px5 = new Color( entrada.getRGB(linha, coluna) );
                px6 = new Color( entrada.getRGB(linha, coluna+1) );
                px7 = new Color( entrada.getRGB(linha+1, coluna-1) );
                px8 = new Color( entrada.getRGB(linha+1, coluna) );
                px9 = new Color( entrada.getRGB(linha+1, coluna+1) );

                int pixel = (int) (
                        (filtro_gau[0] * px1.getRed())+
                                (filtro_gau[1] * px2.getRed())+
                                (filtro_gau[2] * px3.getRed())+
                                (filtro_gau[3] * px4.getRed())+
                                (filtro_gau[4] * px5.getRed())+
                                (filtro_gau[5] * px6.getRed())+
                                (filtro_gau[6] * px7.getRed())+
                                (filtro_gau[7] * px8.getRed())+
                                (filtro_gau[8] * px9.getRed())
                );

                if (pixel > 255) {
                    pixel = 255;
                }
                if (pixel < 0) {
                    pixel = 0;
                }

                Color novaCor = new Color(pixel, pixel, pixel);
                saida.setRGB(linha, coluna, novaCor.getRGB());

            }
        }

        return saida;
    }

    public static BufferedImage realceBorda(BufferedImage entrada) throws IOException {
        int largura = entrada.getWidth();
        int altura = entrada.getHeight();

        BufferedImage saida = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        int[] realce = {-1, -1, -1, -1, 8, -1, -1, -1, -1};

        Color px1, px2, px3, px4, px5, px6, px7, px8, px9;

        for (int linha = 1; linha < largura-2; linha++){
            for (int coluna = 1; coluna < altura-2; coluna++){

                px1 = new Color( entrada.getRGB(linha-1, coluna-1) );
                px2 = new Color( entrada.getRGB(linha-1, coluna) );
                px3 = new Color( entrada.getRGB(linha-1, coluna+1) );
                px4 = new Color( entrada.getRGB(linha, coluna-1) );
                px5 = new Color( entrada.getRGB(linha, coluna) );
                px6 = new Color( entrada.getRGB(linha, coluna+1) );
                px7 = new Color( entrada.getRGB(linha+1, coluna-1) );
                px8 = new Color( entrada.getRGB(linha+1, coluna) );
                px9 = new Color( entrada.getRGB(linha+1, coluna+1) );

                int pixel = (int) (
                        (realce[0] * px1.getRed())+
                                (realce[1] * px2.getRed())+
                                (realce[2] * px3.getRed())+
                                (realce[3] * px4.getRed())+
                                (realce[4] * px5.getRed())+
                                (realce[5] * px6.getRed())+
                                (realce[6] * px7.getRed())+
                                (realce[7] * px8.getRed())+
                                (realce[8] * px9.getRed())
                );

                if (pixel > 255) {
                    pixel = 255;
                }
                if (pixel < 0) {
                    pixel = 0;
                }

                Color novaCor = new Color(pixel, pixel, pixel);
                saida.setRGB(linha, coluna, novaCor.getRGB());

            }
        }

        return saida;
    }
    public static BufferedImage binarizarImagem(BufferedImage imagem, int limiar) throws IOException {

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        BufferedImage imagemBinarizada = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_BINARY);

        for (int linha = 0; linha < largura; linha++) {
            for (int coluna = 0; coluna < altura; coluna++) {

                int pixel = imagem.getRGB(linha, coluna);
                Color cor = new Color(pixel);

                int intensidade = (cor.getRed() + cor.getGreen() + cor.getBlue()) / 3;

                if (intensidade < limiar) {
                    imagemBinarizada.setRGB(linha, coluna, Color.BLACK.getRGB());
                } else {
                    imagemBinarizada.setRGB(linha, coluna, Color.WHITE.getRGB());
                }

            }
        }
        return imagemBinarizada;

    }
    public static BufferedImage inverterCores(BufferedImage imagem) throws IOException {

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int linha = 0; linha < largura; linha++) {
            for (int coluna = 0; coluna < altura; coluna++) {

                int pixel = imagem.getRGB(linha, coluna);
                Color cor = new Color(pixel);

                int corVermelha = 255 - cor.getRed();
                int corVerde = 255 - cor.getGreen();
                int corAzul = 255 - cor.getBlue();

                Color novaCor = new Color(corVermelha, corVerde, corAzul);
                novaImagem.setRGB(linha, coluna, novaCor.getRGB());

            }
        }
        return novaImagem;
    }
    public static BufferedImage aplicarFiltros(BufferedImage imagem, int[] filtro) throws IOException {
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int linha = 1; linha < largura - 1; linha++) {
            for (int coluna = 1; coluna < altura - 1; coluna++) {
                int pixel1 = imagem.getRGB(linha - 1, coluna - 1);
                Color cor1 = new Color(pixel1);
                int px1 = cor1.getRed();

                int pixel2 = imagem.getRGB(linha - 1, coluna);
                Color cor2 = new Color(pixel2);
                int px2 = cor2.getRed();

                int pixel3 = imagem.getRGB(linha - 1, coluna + 1);
                Color cor3 = new Color(pixel3);
                int px3 = cor3.getRed();

                int pixel4 = imagem.getRGB(linha, coluna - 1);
                Color cor4 = new Color(pixel4);
                int px4 = cor4.getRed();

                int pixel5 = imagem.getRGB(linha, coluna);
                Color cor5 = new Color(pixel5);
                int px5 = cor5.getRed();

                int pixel6 = imagem.getRGB(linha, coluna + 1);
                Color cor6 = new Color(pixel6);
                int px6 = cor6.getRed();

                int pixel7 = imagem.getRGB(linha + 1, coluna - 1);
                Color cor7 = new Color(pixel7);
                int px7 = cor7.getRed();

                int pixel8 = imagem.getRGB(linha + 1, coluna);
                Color cor8 = new Color(pixel8);
                int px8 = cor8.getRed();

                int pixel9 = imagem.getRGB(linha + 1, coluna + 1);
                Color cor9 = new Color(pixel9);
                int px9 = cor9.getRed();

                double novoPixel = 0.0;

                novoPixel += (px1 * filtro[0]);
                novoPixel += (px2 * filtro[1]);
                novoPixel += (px3 * filtro[2]);
                novoPixel += (px4 * filtro[3]);
                novoPixel += (px5 * filtro[4]);
                novoPixel += (px6 * filtro[5]);
                novoPixel += (px7 * filtro[6]);
                novoPixel += (px8 * filtro[7]);
                novoPixel += (px9 * filtro[8]);

                if (novoPixel > 255) {
                    novoPixel = 255;
                }
                if (novoPixel < 0) {
                    novoPixel = 0;
                }

                int novoValor = (int) novoPixel;
                Color novaCor = new Color(novoValor, novoValor, novoValor);
                novaImagem.setRGB(linha, coluna, novaCor.getRGB());
            }
        }

        return novaImagem;
    }
}