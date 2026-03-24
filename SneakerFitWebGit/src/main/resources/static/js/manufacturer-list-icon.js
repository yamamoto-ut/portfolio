// manufacturer-list-icon.js タイトルをアイコン表示する
// アイコンの背景色と文字色の組み合わせ
const ICON_COLORS = [
    { bg: '#E6F1FB', text: '#0C447C' }, 
    { bg: '#EEEDFE', text: '#3C3489' },
    { bg: '#FAECE7', text: '#712B13' },
    { bg: '#EAF3DE', text: '#27500A' },
    { bg: '#E1F5EE', text: '#085041' },
    { bg: '#FAEEDA', text: '#633806' },
];
// 各アイコン要素に対して、背景色と文字色を設定し、テキストをメーカー名の頭2文字にする
document.querySelectorAll('.manufacturer-icon').forEach(el => {
    const name = el.dataset.name || ''; // データ属性からメーカー名を取得
    const index = name.charCodeAt(0) % ICON_COLORS.length; // メーカー名の最初の文字のASCIIコードを使って色を選択
    const color = ICON_COLORS[index];
    el.style.backgroundColor = color.bg;
    el.style.color = color.text; // 文字色を設定
    el.textContent = name.slice(0, 2).toUpperCase(); // メーカー名の頭2文字を大文字で表示
});