import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

	var body: some View {
        NavigationView{
            listView()
                .navigationBarTitle("Spaceflight News")
                .navigationBarItems(trailing:
                                        Button("Reload"){
                    self.viewModel.loadNews(forceReload: true)
                })
            
        }.background(Color.black)
	}
    
    private func listView() -> AnyView{
        switch viewModel.news{
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let news):
            return AnyView(ScrollView{
                ForEach(news, id: \.id){item in
                    NewsCard(newsItem: item)
                    Divider()
                }
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView{
    enum LoadableNews{
        case loading
        case result([Item])
        case error(String)
    }
    
    class ViewModel: ObservableObject{
        let sdk: SpaceflightNewsSDK
        @Published var news = LoadableNews.loading
        
        init(sdk: SpaceflightNewsSDK){
            self.sdk = sdk
            self.loadNews(forceReload: true)
        }
        
        func loadNews(forceReload: Bool){
            self.news = .loading
            sdk.getNews(forceReload: forceReload, completionHandler: {news, error in
                if let news = news{
                    self.news = .result(news)
                }else{
                    self.news = .error(error?.localizedDescription ?? "ERROR")
                }
            })
        }
    }
}

