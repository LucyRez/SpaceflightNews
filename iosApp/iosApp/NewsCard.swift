//
//  NewsCard.swift
//  iosApp
//
//  Created by Ludmila Rezunic on 23.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NewsCard: View {
    var newsItem: Item
    
    var body: some View {
        HStack{
            VStack(alignment: .leading, spacing: 10){
                Text(newsItem.title).font(.title2).bold()
                if #available(iOS 15.0, *) {
                    AsyncImage(url: URL(string: newsItem.imageUrl)){image in
                        image.resizable()
                    }placeholder:{
                        ProgressView()
                    }.frame(width: 370, height: 250)
                    
                } else {
                    // Fallback on earlier versions
                }
                Text(newsItem.timePublished.components(separatedBy: "T")[0])
                    .padding(.bottom, 10)
                
                Link("Read Full Article",
                      destination: URL(string: newsItem.articleUrl)!)
                Text("Published By: \(newsItem.newsSite)")
            }
            .padding()
        }
        .padding(.bottom, 5)
        .padding(.top, 10)
        .padding(.leading, 15)
        .padding(.trailing, 15)
    }
}

