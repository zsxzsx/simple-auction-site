/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.sql.Timestamp;
/**
 *
 * @author Rory
 */
public class Auction implements java.io.Serializable
{
    private Integer auctionNo;
    private Timestamp startTime;
    private Timestamp stopTime;
    private String itemName;
    private String itemDesc;
    private Integer highBid;
    private String highBidder;
    private Integer userBid;
    private String sellerId;
    private double sellerRating;


    public Auction(Integer aucNo, Timestamp start, Timestamp stop, String name, String desc,
                    Integer hiBid, String hiBidder)
    {
       setAuctionNo(aucNo);
       setStartTime(start);
       setStopTime(stop);
       setItemName(name);
       setItemDesc(desc);
       setHighBidder(hiBidder);
       setHighBid(hiBid);
    }
    public Auction(Integer aucNo, Timestamp start, Timestamp stop, String name, String desc,
                    Integer hiBid, String hiBidder, Integer userBid)
    {
       setAuctionNo(aucNo);
       setStartTime(start);
       setStopTime(stop);
       setItemName(name);
       setItemDesc(desc);
       setHighBidder(hiBidder);
       setHighBid(hiBid);
       setUserBid(userBid);
    }

    public void setAuctionNo(Integer aucNo)
    {
       auctionNo= aucNo;
    }
    public Integer getAuctionNo()
    {
       return auctionNo;
    }
    public void setStartTime(Timestamp start)
    {
       startTime= start;
    }
    public Timestamp getStartTime()
    {
       return startTime;
    }
    public void setStopTime(Timestamp stop)
    {
       stopTime= stop;
    }
    public Timestamp getStopTime()
    {
       return stopTime;
    }
    public void setItemName(String name)
    {
       itemName= name;
    }
    public String getItemName()
    {
       return itemName;
    }
    public void setItemDesc(String desc)
    {
       itemDesc= desc;
    }
    public String getItemDesc()
    {
       return itemDesc;
    }
    public void setHighBid(Integer hiBid)
    {
       highBid= hiBid;
    }
    public Integer getHighBid()
    {
       return highBid;
    }
    public void setHighBidder(String hiBidder)
    {
       highBidder= hiBidder;
    }
    public String getHighBidder()
    {
       return highBidder;
    }
    public void setUserBid(Integer bid)
    {
       userBid= bid;
    }
    public Integer getUserBid()
    {
       return userBid;
    }
     public void setSellerId(String name)
    {
       sellerId= name;
    }
    public String getSeller()
    {
       return sellerId;
    }
    public void setSellerRating(double rating)
    {
       sellerRating = rating;
    }
    public double getSellerRating()
    {
       return sellerRating;
    }
}
