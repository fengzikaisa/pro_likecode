package com.likecode.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class DownController {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String json="[{'266':'Aatrox','103':'Ahri','84':'Akali','12':'Alistar','32':'Amumu','34':'Anivia','1':'Annie','22':'Ashe','136':'AurelionSol','268':'Azir','432':'Bard','53':'Blitzcrank','63':'Brand','201':'Braum','51':'Caitlyn','164':'Camille','69':'Cassiopeia','31':'Chogath','42':'Corki','122':'Darius','131':'Diana','119':'Draven','36':'DrMundo','245':'Ekko','60':'Elise','28':'Evelynn','81':'Ezreal','9':'Fiddlesticks','114':'Fiora','105':'Fizz','3':'Galio','41':'Gangplank','86':'Garen','150':'Gnar','79':'Gragas','104':'Graves','120':'Hecarim','74':'Heimerdinger','420':'Illaoi','39':'Irelia','427':'Ivern','40':'Janna','59':'JarvanIV','24':'Jax','126':'Jayce','202':'Jhin','222':'Jinx','145':'Kaisa','429':'Kalista','43':'Karma','30':'Karthus','38':'Kassadin','55':'Katarina','10':'Kayle','141':'Kayn','85':'Kennen','121':'Khazix','203':'Kindred','240':'Kled','96':'KogMaw','7':'Leblanc','64':'LeeSin','89':'Leona','127':'Lissandra','236':'Lucian','117':'Lulu','99':'Lux','54':'Malphite','90':'Malzahar','57':'Maokai','11':'MasterYi','21':'MissFortune','62':'MonkeyKing','82':'Mordekaiser','25':'Morgana','267':'Nami','75':'Nasus','111':'Nautilus','518':'Neeko','76':'Nidalee','56':'Nocturne','20':'Nunu','2':'Olaf','61':'Orianna','516':'Ornn','80':'Pantheon','78':'Poppy','555':'Pyke','246':'Qiyana','133':'Quinn','497':'Rakan','33':'Rammus','421':'RekSai','58':'Renekton','107':'Rengar','92':'Riven','68':'Rumble','13':'Ryze','113':'Sejuani','35':'Shaco','98':'Shen','102':'Shyvana','27':'Singed','14':'Sion','15':'Sivir','72':'Skarner','37':'Sona','16':'Soraka','50':'Swain','517':'Sylas','134':'Syndra','223':'TahmKench','163':'Taliyah','91':'Talon','44':'Taric','17':'Teemo','412':'Thresh','18':'Tristana','48':'Trundle','23':'Tryndamere','4':'TwistedFate','29':'Twitch','77':'Udyr','6':'Urgot','110':'Varus','67':'Vayne','45':'Veigar','161':'Velkoz','254':'Vi','112':'Viktor','8':'Vladimir','106':'Volibear','19':'Warwick','498':'Xayah','101':'Xerath','5':'XinZhao','157':'Yasuo','83':'Yorick','350':'Yuumi','154':'Zac','238':'Zed','115':'Ziggs','26':'Zilean','142':'Zoe','143':'Zyra'}]";

        //把String转换为json
        JSONArray jsonArray = JSONArray.fromObject(json);
        JSONObject jsonObject=jsonArray.getJSONObject(0);
        String filename="%s%s.jpg";
        String savePath="d:\\wk\\img\\";
        String urlString="https://ossweb-img.qq.com/images/lol/web201310/skin/big%s00%s.jpg";
        for(int i=1;i<600;i++){
            if(jsonObject.has(String.valueOf(i))){
                String name=jsonObject.getString(String.valueOf(i));
                for(int j=1;j<9;j++){
                    String filename1=String.format(filename,name,j);
                    System.out.println("filename1:"+filename1);
                    String urlString1=String.format(urlString,i,j);
                    System.out.println("filename1:"+filename1);
                    try{
                        download(urlString1,filename1,savePath);
                    }
                    catch (Exception e){

                    }

                }
            }
        }

        // TODO Auto-generated method stub  
//        download("https://ossweb-img.qq.com/images/lol/web201310/skin/big17001.jpg", "1_li1325169021.jpg","d:\\wk\\img\\");
    }

    public static void download(String urlString, String filename,String savePath) throws Exception {
        // 构造URL  
        URL url = new URL(urlString);
        // 打开连接  
        URLConnection con = url.openConnection();
        //设置请求超时为5s  
        con.setConnectTimeout(5*1000);
        // 输入流  
        InputStream is = con.getInputStream();

        // 1K的数据缓冲  
        byte[] bs = new byte[1024];
        // 读取到的数据长度  
        int len;
        // 输出的文件流  
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
        // 开始读取  
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接  
        os.close();
        is.close();
    }

}